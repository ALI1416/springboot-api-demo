package com.demo.controller.admin;

import cn.z.id.Id;
import com.demo.base.ControllerBase;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.excel.UserExport;
import com.demo.entity.excel.UserImport;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.UserVo;
import com.demo.service.UserService;
import com.demo.util.*;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <h1>用户管理api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
//@Auth
@RestController
@RequestMapping("admin/user")
@AllArgsConstructor
public class UserManageController extends ControllerBase {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    /**
     * 插入(需account,pwd)
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody UserVo user) {
        if (existEmpty(user.getAccount(), user.getPwd())) {
            return Result.e1();
        }
        // 用户已存在
        if (userService.existAccount(user.getAccount())) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        Long id = AuthUtils.getUserId(request);
        user.setId(Id.next());
        user.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        user.setCreateId(id);
        return userService.insert(user);
    }

    /**
     * 导出
     */
    @PostMapping("exportExcel")
    public void exportExcel() {
        EeUtils.download(response, "用户信息", UserExport.class, userService.export(null));
    }

    /**
     * 导入模板
     */
    @PostMapping("importTemplate")
    public void importTemplate() {
        EeUtils.download(response, "导入用户模板", UserImport.class, null);
    }

    /**
     * 导入
     */
    @PostMapping("importExcel")
    public Result importExcel(MultipartFile file) {
        List<UserImport> list = new ArrayList<>();
        EeUtils.upload(file, UserImport.class, list);
        return batchInsert(list);
    }

    /**
     * 批量插入(需account,pwd)
     */
    @PostMapping("/batchInsert")
    public Result batchInsert(@RequestBody List<UserImport> users) {
        if (users == null || users.size() == 0) {
            return Result.e1();
        }
        // 无效的返回结果
        ResultBatch<String> invalidResult = new ResultBatch<>();
        // 有效的account列表
        List<String> validAccountList = new ArrayList<>();
        // 有效的用户列表
        List<UserImport> validUserList = new ArrayList<>();
        // 检查数据完整性和是否重复
        for (UserImport u : users) {
            if (isEmpty(u.getAccount()) || !RegexUtils.isAccount(u.getAccount())) {
                invalidResult.add(false, u.getAccount(), "账号不符合规范");
            } else if (isEmpty(u.getPwd())) {
                invalidResult.add(false, u.getAccount(), "密码不能为空");
            } else if (validAccountList.contains(u.getAccount())) {
                invalidResult.add(false, u.getAccount(), "账号存在重复");
            } else {
                validAccountList.add(u.getAccount());
                validUserList.add(u);
            }
        }
        /* 有效用户为0 */
        if (validUserList.size() == 0) {
            return Result.o(invalidResult);
        }
        // 查询account是否被注册
        List<UserVo> existUserList = userService.findByAccountList(validAccountList);
        // 未注册用户列表
        List<UserImport> unregisteredUserList = new ArrayList<>();
        // 检查用户是否已注册
        if (existUserList.size() == 0) {
            unregisteredUserList = validUserList;
        } else {
            // 如果存在账号被注册，提取出account
            List<String> existAccountList = existUserList.stream().map(UserVo::getAccount).collect(Collectors.toList());
            for (UserImport u : validUserList) {
                if (existAccountList.contains(u.getAccount())) {
                    invalidResult.add(false, u.getAccount(), "账号已被注册");
                } else {
                    unregisteredUserList.add(u);
                }
            }
        }
        /* 未注册的用户为0 */
        if (unregisteredUserList.size() == 0) {
            return Result.o(invalidResult);
        }
        // 准备批量插入的用户
        List<UserVo> batchInsertUserList = new ArrayList<>();
        Long id = AuthUtils.getUserId(request);
        for (UserImport u : unregisteredUserList) {
            UserVo uv = new UserVo();
            uv.setId(Id.next());
            uv.setAccount(u.getAccount());
            uv.setPwd(EncoderUtils.bCrypt(u.getPwd()));
            uv.setCreateId(id);
            batchInsertUserList.add(uv);
        }
        // 插入后的返回结果
        ResultBatch<String> batchInsertResult = userService.batchInsert(batchInsertUserList);
        /* 合并返回结果 */
        return Result.o(ResultBatch.merge(invalidResult, batchInsertResult));
    }

    /**
     * es导入用户数据
     */
    @PostMapping("/es/importUser")
    public Result esImportUser() {
        String index = "user";
        // 不存在索引，去创建
        if (!EsUtils.existIndex(index)) {
            String mapping = "{\n" + //
                    "  \"properties\": {\n" + //
                    "    \"profile\": {\n" + // 字段名
                    "      \"type\": \"text\",\n" + // 类型keyword、text
                    "      \"analyzer\": \"ik_max_word\",\n" + // 分词器standard、ik_smart、ik_max_word
                    "      \"search_analyzer\": \"ik_max_word\"\n" + // 搜索用分词器
                    "    },\n" + //
                    "    \"comment\": {\n" + // 字段名
                    "      \"type\": \"text\",\n" + // 类型keyword、text
                    "      \"analyzer\": \"ik_max_word\",\n" + // 分词器standard、ik_smart、ik_max_word
                    "      \"search_analyzer\": \"ik_max_word\"\n" + // 搜索用分词器
                    "    }\n" + //
                    "  }\n" + //
                    "}";
            if (!EsUtils.createIndex(index, mapping)) {
                return Result.e();
            }
        }
        // 查询用户数据
        List<UserVo> list = userService.esFind(null);
        Map<String, UserVo> map = new HashMap<>();
        for (UserVo u : list) {
            map.put(String.valueOf(u.getId()), u);
        }
        // 导入用户数据
        if (!EsUtils.addDocumentBulk(index, map)) {
            return Result.e();
        }
        return Result.o();
    }

    /**
     * es查询
     */
    @PostMapping("/es/search")
    public Result esSearch(@RequestBody UserVo user) {
        String index = "user";
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("profile", user.getProfile());
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("profile")// 匹配字段
                // .requireFieldMatch(false)// 匹配所有字段
                // .preTags("<span style='color:red'>")// 内容前缀
                // .postTags("</span>")// 内容后缀
                ;
        SearchResponse searchResponse = EsUtils.search(index, queryBuilder, highlightBuilder, 1, 10, null);
        return Result.o(EsUtils.extractHighlightResult(searchResponse));
    }

    /**
     * 精确查询
     */
    @PostMapping("/findExact")
    public Result findExact(@RequestBody @Nullable UserVo user) {
        return userService.findExact(user);
    }

    /**
     * 模糊查询
     */
    @PostMapping("/find")
    public Result find(@RequestBody @Nullable UserVo user) {
        return userService.find(user);
    }
}
