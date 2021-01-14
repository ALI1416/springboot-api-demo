package com.demo.util;

import com.demo.constant.RedisConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>权限认证工具</h1>
 * 
 * <p>
 * createDate 2021/01/13 21:25:56
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class AuthUtils {

    public static String getRedisId(HttpServletRequest request) {
        return request.getHeader(RedisConstant.REDIS_SIGN_NAME);
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader(RedisConstant.TOKEN_NAME);
    }
}
