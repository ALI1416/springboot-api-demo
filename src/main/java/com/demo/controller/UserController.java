package com.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;
import com.demo.result.Result;
import com.demo.result.ResultCode;
import com.demo.service.UserService;

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

    // @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (user.getAccount() == null || user.getPwd() == null || user.getName() == null || user.getAccount().length() <= 0 || user.getPwd().length() != 32 || user.getName().length() <= 0) {
            return Result.e(ResultCode.PARAM_IS_ERROR);
        }
        // 用户已存在
        if (userService.exist(user.getAccount())) {
            return Result.e(ResultCode.USER_HAS_EXISTED);
        }
        if (user.getGender() == null) {
            user.setGender(0);
        }
        if (user.getYear() == null) {
            user.setYear(2000);
        }
        if (user.getIsDelete() == null) {
            user.setIsDelete(0);
        }
        User u = userService.insert(user);
        if (u != null) {
            return Result.ok(u);
        } else {
            return Result.e();
        }
    }
}
