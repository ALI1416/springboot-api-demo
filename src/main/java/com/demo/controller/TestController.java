package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.pojo.Result;
import com.demo.service.TestService;
import com.demo.util.ClientInfoUtils;
import com.demo.util.UserAgentUtils;
import com.demo.util.pojo.UserAgentInfo;

import lombok.AllArgsConstructor;

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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private HttpServletRequest request;
    private TestService testService;

    @GetMapping("")
    public Result index() {
        UserAgentInfo a = UserAgentUtils.getUserAgentInfo(ClientInfoUtils.getUserAgent(request));
        return Result.o(a);
    }

    @GetMapping("/a")
    public Result a() {
        return Result.o(testService.a(request));
    }

}
