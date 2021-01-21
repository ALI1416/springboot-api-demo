package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.constant.Constant;
import com.demo.property.MailProperty;
import com.demo.property.MyProperty;
import com.demo.tool.Log;

/**
 * <h1>常量api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/const")
@RestController
public class ConstantController {
    @GetMapping("")
    public String index() {
        Log.i(Constant.A + "");
        Log.i(Constant.B + "");
        Log.i(Constant.C + "");
        Log.i(MailProperty.USERNAME + "");
        Log.i(MailProperty.PASSWORD + "");
        Log.i(MailProperty.HOST + "");
        Log.i(MailProperty.PROTOCOL + "");
        Log.i(MailProperty.DEFAULT_ENCODING + "");
        Log.i(MyProperty.A + "");
        Log.i(MyProperty.B + "");
        Log.i(MyProperty.C + "");
        Log.i(MyProperty.D + "");
        Log.i(MyProperty.E + "");
        return "ok";
    }
}
