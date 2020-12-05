package com.demo.controller;

import com.demo.util.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
@RequestMapping("/redis")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RedisController {

    private final RedisUtils redisUtil;

    /**
     * redis中存储的过期时间60s
     */
    private final static int ExpireTime = 60;

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
