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

    /**
     * 获取sign
     *
     * @param request HttpServletRequest
     */
    public static String getSign(HttpServletRequest request) {
        return request.getHeader(RedisConstant.SIGN_NAME);
    }

    /**
     * 获取token
     *
     * @param request HttpServletRequest
     */
    public static String getToken(HttpServletRequest request) {
        return request.getHeader(RedisConstant.TOKEN_NAME);
    }

    /**
     * 获取userId
     *
     * @param request HttpServletRequest
     */
    public static Long getUserId(HttpServletRequest request) {
        String sign = request.getHeader(RedisConstant.SIGN_NAME);
        return (Long) RedisUtils.hashGet(sign, RedisConstant.USER_ID_NAME);
    }

}
