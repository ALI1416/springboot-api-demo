package com.demo.controller;

import com.demo.constant.ResultCodeEnum;
import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.UserVo;
import com.demo.service.UserService;
import com.demo.tool.Id;
import com.demo.util.EncoderUtils;
import com.demo.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <h1>User api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    /**
     * 是否存在该id
     */
    @PostMapping("/existId")
    public Result existId(long id) {
        return userService.existId(id);
    }

    /**
     * 是否存在该账号
     */
    @PostMapping("/existAccount")
    public Result existAccount(String account) {
        if (StringUtils.existEmpty(account)) {
            return Result.e1();
        }
        return userService.existAccount(account);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (StringUtils.existEmpty(user.getAccount(), user.getPwd()) || user.getPwd().length() != 32) {
            return Result.e1();
        }
        user.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        if (StringUtils.existEmpty(user.getName())) {
            user.setName(user.getAccount());
        }
        user.setId(Id.next());
        return userService.register(user);
    }

    /**
     * 查找用户，通过id
     */
    @PostMapping("/findById")
    public Result findById(long id) {
        return userService.findById(id);
    }

    /**
     * 查找用户，通过account
     */
    @PostMapping("/findByAccount")
    public Result findByAccount(String account) {
        return userService.findByAccount(account);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if ((StringUtils.existEmpty(user.getAccount(), user.getPwd())) || user.getPwd().length() != 32) {
            return Result.e1();
        }
        return userService.login(user);
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody User user) {
        if (user.getId() == null) {
            return Result.e1();
        }
        return userService.changeInfo(user);
    }

    /**
     * 修改密码
     */
    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody UserVo user) {
        if (user.getId() == null || user.getPwd() == null || user.getNewPwd() == null || user.getPwd().length() != 32 || user.getNewPwd().length() != 32) {
            return Result.e1();
        }
        return userService.changePwd(user);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteById")
    public Result deleteById(long id) {
        return userService.deleteById(id);
    }

    /**
     * 批量插入(明文密码)
     */
    @PostMapping("/batchRegister")
    public Result batchRegister(@RequestBody List<User> user) {
        // 数据完整性检查
        ResultBatch<User> result = new ResultBatch<>();
        for (User u : user) {
            if (StringUtils.existEmpty(u.getAccount())) {
                result.add(false, u, "账号不能为空");
            } else {
                result.add(u);
            }
        }
        // 数据不完整
        if (!result.isOk()) {
            return Result.e(ResultCodeEnum.BATCH_OPERATION_ERROR, result);
        }
        // 补充缺失信息
        for (User u : user) {
            if (StringUtils.existEmpty(u.getPwd())) {
                u.setPwd(u.getAccount());
            }
            u.setPwd(EncoderUtils.bCrypt(EncoderUtils.md5(u.getPwd())));
            if (StringUtils.existEmpty(u.getName())) {
                u.setName(u.getAccount());
            }
            u.setId(Id.next());
        }
        return userService.batchRegister(user);
    }

    /**
     * 精确查询
     */
    @PostMapping("/findExact")
    public Result findExact(@RequestBody @Nullable User user) {
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
