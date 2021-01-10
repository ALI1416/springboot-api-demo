package com.demo.annotation;

import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;

/**
 * <h1>权限认证注解实现</h1>
 *
 * <p>
 * createDate 2020/12/06 11:45:48
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class AuthImpl {

    /**
     * 检查token是否正确
     *
     * @param timestamp timestamp
     * @param token     token
     */
    public static boolean authToken(String timestamp, String token) {
        System.out.println(timestamp);
        if (StringUtils.existEmpty(timestamp, token)) {
            return false;
        }
        String redisToken = (String) RedisUtils.hashGet(timestamp, "token");
        return token.equals(redisToken);
    }
}
