package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.constant.Constant;
import com.demo.constant.MailConstant;
import com.demo.constant.MyConstant;
import com.demo.tool.Log;

/**
 * <h1>常数测试api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
@RequestMapping("/const")
@RestController
public class ConstantController {
    @GetMapping("")
    public String index() {
        Log.i(Constant.A + "");
        Log.i(Constant.B + "");
        Log.i(Constant.C + "");
        Log.i(MailConstant.USERNAME + "");
        Log.i(MailConstant.PASSWORD + "");
        Log.i(MailConstant.HOST + "");
        Log.i(MailConstant.PROTOCOL + "");
        Log.i(MailConstant.DEFAULT_ENCODING + "");
        Log.i(MyConstant.A + "");
        Log.i(MyConstant.B + "");
        Log.i(MyConstant.C + "");
        Log.i(MyConstant.D + "");
        Log.i(MyConstant.E + "");
        return "ok";
    }
}
