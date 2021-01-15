package com.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.demo.entity.pojo.Result;
import com.demo.util.ClientInfoUtils;
import com.demo.util.UserAgentUtils;
import com.demo.util.pojo.UserAgentInfo;

/**
 * <h1>测试服务</h1>
 *
 * <p>
 * createDate 2021/01/15 15:23:13
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Service
public class TestService {

    public Result a(HttpServletRequest request) {
        UserAgentInfo a = UserAgentUtils.getUserAgentInfo(ClientInfoUtils.getUserAgent(request));
        return Result.o(a);
    }
}
