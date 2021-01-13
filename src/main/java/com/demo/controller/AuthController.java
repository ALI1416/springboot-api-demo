package com.demo.controller;

import com.demo.annotation.Auth;
import com.demo.constant.RedisConstant;
import com.demo.entity.pojo.Result;
import com.demo.tool.Id;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
@Auth
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Auth(skip = true)
    @PostMapping("getToken")
    public Result getToken() {
        long redisId = Id.next();
        String token = StringUtils.getRandom(StringUtils.NUMBER_LOWER_LETTER, RedisConstant.TOKEN_LENGTH);
        RedisUtils.hashSet(String.valueOf(redisId), RedisConstant.TOKEN_NAME, token, RedisConstant.REDIS_EXPIRE_TIME);
        Map<String, Object> map = new HashMap<>();
        map.put(RedisConstant.REDIS_ID_NAME, redisId);
        map.put(RedisConstant.TOKEN_NAME, token);
        return Result.o(map);
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
