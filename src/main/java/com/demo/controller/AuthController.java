package com.demo.controller;

import com.demo.annotation.Auth;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>权限认证api</h1>
 *
 * <p>
 * createDate 2020/12/05 19:39:35
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
@Auth
@RequestMapping("/auth")
@RestController
public class AuthController {

    @PostMapping("getAuth")
    public String getAuth() {
        return null;
    }

    @Auth(skip = true)
    @PostMapping("skipAuth")
    public String skipAuth() {
        return null;
    }
}
