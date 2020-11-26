package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;
import com.demo.result.Result;
import com.demo.service.UserService;
import com.demo.util.EncoderUtils;
import com.demo.vo.UserVo;

import lombok.AllArgsConstructor;

/**
 * <h1>User控制层</h1>
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

    private final UserService USER;

    /**
     * 是否存在该账号
     */
    @PostMapping("/existAccount")
    public Result existAccount(String account) {
        if (account == null || account.length() == 0) {
            return Result.e1();
        }
        return USER.existAccount(account);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (user.getAccount() == null || user.getPwd() == null || user.getName() == null
                || user.getAccount().length() == 0 || user.getPwd().length() != 32 || user.getName().length() == 0) {
            return Result.e1();
        }
        user.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        if (user.getGender() == null) {
            user.setGender(0);
        }
        if (user.getYear() == null) {
            user.setYear(2000);
        }
        if (user.getIsDelete() == null) {
            user.setIsDelete(0);
        }
        return USER.register(user);
    }

    /**
     * 查找用户，通过id
     */
    @PostMapping("/findById")
    public Result findById(int id) {
        return USER.findById(id);
    }

    /**
     * 查找用户，通过account
     */
    @PostMapping("/findByAccount")
    public Result findById(String account) {
        return USER.findByAccount(account);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (user.getAccount() == null || user.getPwd() == null || user.getAccount().length() == 0
                || user.getPwd().length() != 32) {
            return Result.e1();
        }
        return USER.login(user);
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody User user) {
        if (user.getId() == null) {
            return Result.e1();
        }
        return USER.changeInfo(user);
    }

    /**
     * 修改密码
     */
    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody UserVo user) {
        if (user.getId() == null || user.getPwd() == null || user.getNewPwd() == null || user.getPwd().length() != 32
                || user.getNewPwd().length() != 32) {
            return Result.e1();
        }
        return USER.changePwd(user);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        return USER.deleteById(id);
    }

}
