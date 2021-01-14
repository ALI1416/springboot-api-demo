package com.demo.controller;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;

/**
 * <h1>测试api</h1>
 *
 * <p>
 * createDate 2021/01/14 14:32:52
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("test")
public class TestController {
    @PostMapping("/a")
    public Result a() {
        User u = new User();
        u.setAccount("aaaaa");
        u.setId(121212L);
        u.setCreateTime(new Timestamp(1610352200549L));
        return Result.o(u);
    }

}
