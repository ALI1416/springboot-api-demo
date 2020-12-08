package com.demo.annotation;

import com.demo.entity.pojo.Redis;
import com.demo.util.RedisUtils;

/**
 * <h1>权限认证注解实现</h1>
 *
 * <p>
 * createDate 2020/12/06 11:45:48
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
public class AuthImpl {

    /**
     * 检查token是否正确
     *
     * @param id    id
     * @param token token
     */
    public static boolean authToken(String id, String token) {
        if (id == null || token == null || id.length() == 0 || token.length() == 0) {
            return false;
        }
        Redis redis = (Redis) RedisUtils.get(id);
        return token.equals(redis.getToken());
    }
}
