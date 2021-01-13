package com.demo.util;

import com.demo.constant.RedisConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>TODO AuthUtils Title</h1>
 *
 * <p>
 * TODO AuthUtils Description
 * </p>
 *
 * <p>
 * createDate 2021/01/13 21:25:56
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
public class AuthUtils {

    public static String getRedisId(HttpServletRequest request) {
        return request.getHeader(RedisConstant.REDIS_ID_NAME);
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader(RedisConstant.TOKEN_NAME);
    }
}
