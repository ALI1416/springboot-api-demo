package com.demo.controller.test;

import cn.z.id.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Id api</h1>
 *
 * <p>
 * createDate 2020/12/23 16:44:13
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/id")
@RestController
public class IdController {

    @GetMapping("get")
    public long get() {
        return Id.next();
    }

}
