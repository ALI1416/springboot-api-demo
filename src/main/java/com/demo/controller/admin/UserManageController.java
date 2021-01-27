package com.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;

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
        // 无效的结果
        ResultBatch<String> invalidResult = new ResultBatch<>();
        // 有效的账号列表
        List<String> validAccountList = new ArrayList<>();
        // 数据检查
        for (UserVo u : users) {
            if (existEmpty(u.getAccount(), u.getPwd())) {
                invalidResult.add(false, u.getAccount(), "账号和密码不能为空");
            } else if (validAccountList.contains(u.getAccount())) {
                invalidResult.add(false, u.getAccount(), "账号重复");
            } else {
                validAccountList.add(u.getAccount());
            }
        }
        // 账号是否存在
        List<UserVo> a=userService.findByAccountList(validAccountList);

        // 数据有误
        if (!result.isOk()) {
            return Result.e(ResultCodeEnum.BATCH_OPERATION_ERROR, result);
        }
        Long id = AuthUtils.getUserId(request);
        for (UserVo u : users) {
            u.setId(Id.next());
            u.setPwd(EncoderUtils.bCrypt(u.getPwd()));
            u.setCreateId(id);
        }
        return userService.batchInsert(users);
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
