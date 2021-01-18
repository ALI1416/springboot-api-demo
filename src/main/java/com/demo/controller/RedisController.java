package com.demo.controller;

import com.demo.util.RedisUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Redis api</h1>
 *
 * <p>
 * createDate 2020/12/05 19:39:35
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/redis")
@RestController
public class RedisController {

    @PostMapping("set")
    public String redisSet(String key, String value) {
        RedisUtils.set(key, value);
        return "ok";
    }

    @PostMapping("get")
    public Object redisGet(String key) {
        return RedisUtils.get(key);
    }

    @PostMapping("expire")
    public Boolean redisExpire(String key) {
        return RedisUtils.expire(key, 60);
    }

    @PostMapping("delete")
    public Boolean redisDelete(String key) {
        return RedisUtils.delete(key);
    }
}
