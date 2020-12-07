package com.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.entity.Redis;
import com.demo.entity.Result;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;

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

    @Auth(skip = true)
    @PostMapping("getAuth")
    public Result getAuth(int id) {
        String token = StringUtils.getRandom(StringUtils.NUMBER_LOWER_LETTER, 128);
        Redis redis = new Redis();
        redis.setId(id);
        redis.setToken(token);
        RedisUtils.set(String.valueOf(id), redis, 1000);
        return Result.o(redis);
    }

    @PostMapping("login")
    public String login() {
        return "ok";
    }

    @Auth(skip = true)
    @PostMapping("login2")
    public String login2() {
        return "ok";
    }
}
