package com.demo.util;

import com.demo.constant.CaptchaTypeEnum;
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
     * 设置userId
     *
     * @param request HttpServletRequest
     */
    public static void setUserId(HttpServletRequest request, Long userId) {
        String sign = request.getHeader(RedisConstant.SIGN_NAME);
        RedisUtils.hashSet(sign, RedisConstant.USER_ID_NAME, userId);
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

    /**
     * 设置普通图片验证码(大小写不敏感)
     *
     * @param request HttpServletRequest
     * @param captcha 图片验证码
     */
    public static void setCaptcha(HttpServletRequest request, String captcha) {
        setCaptcha(request, CaptchaTypeEnum.DEFAULT, captcha);
    }

    /**
     * 普通图片验证码是否正确(大小写不敏感)
     *
     * @param request HttpServletRequest
     * @param captcha 图片验证码
     * @return null:过期,true:正确,false:错误
     */
    public static Boolean correctCaptcha(HttpServletRequest request, String captcha) {
        return correctCaptcha(request, CaptchaTypeEnum.DEFAULT, captcha);
    }

    /**
     * 设置图片验证码(大小写不敏感)
     *
     * @param request         HttpServletRequest
     * @param captchaTypeEnum 图片验证码类型枚举
     * @param captcha         图片验证码
     */
    public static void setCaptcha(HttpServletRequest request, CaptchaTypeEnum captchaTypeEnum, String captcha) {
        String sign = request.getHeader(RedisConstant.SIGN_NAME);
        String name = sign + RedisConstant.CAPTCHA_INFIX + captchaTypeEnum.getType();
        RedisUtils.set(name, captcha.toLowerCase(), captchaTypeEnum.getExpire());
    }

    /**
     * 图片验证码是否正确(大小写不敏感)
     *
     * @param request         HttpServletRequest
     * @param captchaTypeEnum 图片验证码类型枚举
     * @param captcha         图片验证码
     * @return null:过期,true:正确,false:错误
     */
    public static Boolean correctCaptcha(HttpServletRequest request, CaptchaTypeEnum captchaTypeEnum, String captcha) {
        String sign = request.getHeader(RedisConstant.SIGN_NAME);
        String name = sign + RedisConstant.CAPTCHA_INFIX + captchaTypeEnum.getType();
        String redisCaptcha = (String) RedisUtils.get(name);
        if (redisCaptcha == null) {
            return null;
        }
        // 验证后一定要删除
        RedisUtils.delete(name);
        return redisCaptcha.equals(captcha.toLowerCase());
    }

    /**
     * 设置邮箱验证码(大小写不敏感)
     *
     * @param request HttpServletRequest
     * @param captcha 验证码
     */
    public static void setEmailCaptcha(HttpServletRequest request, String captcha) {
        String sign = request.getHeader(RedisConstant.SIGN_NAME);
        String name = sign + RedisConstant.EMAIL_CAPTCHA_SUFFIX_NAME;
        RedisUtils.set(name, captcha.toLowerCase(), RedisConstant.EMAIL_CAPTCHA_EXPIRE_TIME);
    }

    /**
     * 邮箱验证码是否正确(大小写不敏感)
     *
     * @param request HttpServletRequest
     * @param captcha 验证码
     * @return null:过期,true:正确,false:错误
     */
    public static Boolean correctEmailCaptcha(HttpServletRequest request, String captcha) {
        String sign = request.getHeader(RedisConstant.SIGN_NAME);
        String name = sign + RedisConstant.EMAIL_CAPTCHA_SUFFIX_NAME;
        String redisCaptcha = (String) RedisUtils.get(name);
        if (redisCaptcha == null) {
            return null;
        }
        // 验证后一定要删除
        RedisUtils.delete(name);
        return redisCaptcha.equals(captcha.toLowerCase());
    }

}
