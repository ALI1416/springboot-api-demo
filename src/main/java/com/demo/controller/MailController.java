package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.util.MailUtils;

/**
 * <h1>邮件api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/mail")
@RestController
public class MailController {

    @GetMapping("")
    public String index() {
        MailUtils.sendMail("1416978277@qq.com", "主题", "内容");
        return "ok";
    }

    @GetMapping("html")
    public String html() {
        MailUtils.sendMailHtml("1416978277@qq.com", "主题Html", "<h1>内容Html</h1>");
        return "ok";
    }
}
