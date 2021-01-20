package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.RedisConstant;
import com.demo.util.AuthUtils;
import com.demo.util.RedisUtils;

import lombok.AllArgsConstructor;

/**
 * <h1>权限认证api</h1>
 *
 * <p>
 * createDate 2020/12/05 19:39:35
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/auth2")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Auth2Controller {

    private final HttpServletRequest request;

    @PostMapping("login")
    public String login() {
        String redisSign = AuthUtils.getSign(request);
        RedisUtils.hashSet(String.valueOf(redisSign), RedisConstant._USER_ID, 1L, RedisConstant.EXPIRE);
        return "ok";
    }

    @Auth(skipLogin = true)
    @PostMapping("login2")
    public String login2() {
        return "ok";
    }
}
