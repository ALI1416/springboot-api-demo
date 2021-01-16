package com.demo.annotation;

import com.demo.constant.RedisConstant;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.pojo.Result;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

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
     * 普通权限
     *
     * @param request HttpServletRequest
     */
    public static Result token(HttpServletRequest request) {
        String sign = request.getHeader(RedisConstant.SIGN_NAME);
        String token = request.getHeader(RedisConstant.TOKEN_NAME);
        if (StringUtils.existEmpty(sign, token)) {
            return Result.e(ResultCodeEnum.TOKEN_IS_EXPIRED);
        }
        // 查找token
        String redisToken = (String) RedisUtils.hashGet(sign, RedisConstant.TOKEN_NAME);
        // token错误
        if (!token.equals(redisToken)) {
            return Result.e(ResultCodeEnum.TOKEN_IS_EXPIRED);
        }
        return Result.o();
    }

    /**
     * 登录权限
     *
     * @param request HttpServletRequest
     */
    public static Result login(HttpServletRequest request) {
        String sign = request.getHeader(RedisConstant.SIGN_NAME);
        String token = request.getHeader(RedisConstant.TOKEN_NAME);
        if (StringUtils.existEmpty(sign, token)) {
            return Result.e(ResultCodeEnum.TOKEN_IS_EXPIRED);
        }
        // 查找token
        String redisToken = (String) RedisUtils.hashGet(sign, RedisConstant.TOKEN_NAME);
        // token错误
        if (!token.equals(redisToken)) {
            return Result.e(ResultCodeEnum.TOKEN_IS_EXPIRED);
        }
        // 查找userId
        Long userId = (Long) RedisUtils.hashGet(sign, RedisConstant.USER_ID_NAME);
        // userId为空
        if (userId == null) {
            return Result.e(ResultCodeEnum.USER_NOT_LOGGED_IN);
        }
        // userId为0
        if (userId == 0) {
            return Result.e(ResultCodeEnum.USER_OFFLINE);
        }
        return Result.o();
    }

}
