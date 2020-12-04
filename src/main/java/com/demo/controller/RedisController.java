package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.util.RedisUtils;

@RequestMapping("/redis")
@RestController
public class RedisController {

    /**
     * redis中存储的过期时间60s
     */
    private static int ExpireTime = 60;

    @Autowired
    private RedisUtils redisUtil;

    @PostMapping("set")
    public boolean redisSet(String key, String value) {
        return redisUtil.set(key, value);
    }

    @PostMapping("get")
    public Object redisGet(String key) {
        return redisUtil.get(key);
    }

    @PostMapping("expire")
    public boolean redisExpire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }
}
