package com.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.ResultCodeEnum;
import com.demo.controller.BaseController;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.UserVo;
import com.demo.service.UserService;
import com.demo.tool.Id;
import com.demo.util.AuthUtils;
import com.demo.util.EncoderUtils;
import com.demo.util.RegexUtils;

import lombok.AllArgsConstructor;

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
@Auth
@RestController
@RequestMapping("admin/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserManageController extends BaseController {

    private final HttpServletRequest request;
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
     * 批量插入(需account,pwd)
     */
    @PostMapping("/batchInsert")
    public Result batchInsert(@RequestBody List<UserVo> users) {
        if (users == null || users.size() == 0) {
            return Result.e1();
        }
        // 无效的返回结果
        ResultBatch<String> invalidResult = new ResultBatch<>();
        // 有效的account列表
        List<String> validAccountList = new ArrayList<>();
        // 有效的用户列表
        List<UserVo> validUserList = new ArrayList<>();
        // 检查数据完整性和是否重复
        for (UserVo u : users) {
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
        List<UserVo> unregisteredUserList = new ArrayList<>();
        // 检查用户是否已注册
        if (existUserList.size() == 0) {
            unregisteredUserList = validUserList;
        } else {
            // 如果存在账号被注册，提取出account
            List<String> existAccountList = existUserList.stream().map(UserVo::getAccount).collect(Collectors.toList());
            for (UserVo u : validUserList) {
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
        // 补充其他信息
        Long id = AuthUtils.getUserId(request);
        for (UserVo u : unregisteredUserList) {
            u.setId(Id.next());
            u.setPwd(EncoderUtils.bCrypt(u.getPwd()));
            u.setCreateId(id);
        }
        // 插入后的返回结果
        ResultBatch<String> batchInsertResult = userService.batchInsert(unregisteredUserList);
        /* 合并返回结果 */
        return Result.o(ResultBatch.merge(invalidResult, batchInsertResult));
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
