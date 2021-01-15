package com.demo.util;

import javax.servlet.http.HttpServletRequest;

import com.demo.constant.RedisConstant;

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

    /**
     * 获取REDIS_SIGN_NAME
     * 
     * @param request HttpServletRequest
     */
    public static String getRedisSign(HttpServletRequest request) {
        return request.getHeader(RedisConstant.REDIS_SIGN_NAME);
    }

    /**
     * 获取TOKEN_NAME
     * 
     * @param request HttpServletRequest
     */
    public static String getToken(HttpServletRequest request) {
        return request.getHeader(RedisConstant.TOKEN_NAME);
    }
}
