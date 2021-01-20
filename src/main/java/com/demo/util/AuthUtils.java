package com.demo.util;

import com.demo.constant.CaptchaTypeEnum;
import com.demo.constant.RedisConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
        return request.getHeader(RedisConstant.SIGN);
    }
    
    /**
     * 设置userId
     *
     * @param request HttpServletRequest
     */
    public static void setUserId(HttpServletRequest request, Long userId) {
        String sign = request.getHeader(RedisConstant.SIGN);
        RedisUtils.hashSet(sign, RedisConstant._USER_ID, userId);
    }

    /**
     * 获取userId
     *
     * @param request HttpServletRequest
     */
    public static Long getUserId(HttpServletRequest request) {
        String sign = request.getHeader(RedisConstant.SIGN);
        return (Long) RedisUtils.hashGet(sign, RedisConstant._USER_ID);
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
    public static boolean correctCaptcha(HttpServletRequest request, String captcha) {
        return correctCaptcha(request, CaptchaTypeEnum.DEFAULT, captcha);
    }

    /**
     * 设置图片验证码
     *
     * @param request         HttpServletRequest
     * @param captchaTypeEnum 图片验证码类型枚举
     * @param captcha         图片验证码
     */
    public static void setCaptcha(HttpServletRequest request, CaptchaTypeEnum captchaTypeEnum, String captcha) {
        String sign = request.getHeader(RedisConstant.SIGN);
        String name = sign + RedisConstant.CAPTCHA_INFIX + captchaTypeEnum.getType();
        RedisUtils.set(name, captcha, captchaTypeEnum.getExpire());
    }

    /**
     * 图片验证码是否正确(大小写不敏感)
     *
     * @param request         HttpServletRequest
     * @param captchaTypeEnum 图片验证码类型枚举
     * @param captcha         图片验证码
     */
    public static boolean correctCaptcha(HttpServletRequest request, CaptchaTypeEnum captchaTypeEnum, String captcha) {
        String sign = request.getHeader(RedisConstant.SIGN);
        String name = sign + RedisConstant.CAPTCHA_INFIX + captchaTypeEnum.getType();
        String redisCaptcha = (String) RedisUtils.get(name);
        if (redisCaptcha == null) {
            return false;
        }
        // 验证后一定要删除(无论成功失败)
        RedisUtils.delete(name);
        return redisCaptcha.equalsIgnoreCase(captcha);
    }

    /**
     * 设置邮箱验证码
     *
     * @param request HttpServletRequest
     * @param email   邮箱地址
     * @param captcha 验证码
     */
    public static void setEmailCaptcha(HttpServletRequest request, String email, String captcha) {
        String sign = request.getHeader(RedisConstant.SIGN);
        String name = sign + RedisConstant.EMAIL_CAPTCHA_SUFFIX;
        RedisUtils.hashSet(name, RedisConstant.EMAIL_CAPTCHA__EMAIL, email,
                RedisConstant.EMAIL_CAPTCHA_EXPIRE);
        RedisUtils.hashSet(name, RedisConstant.EMAIL_CAPTCHA__CAPTCHA, captcha);
    }

    /**
     * 邮箱验证码是否正确(大小写不敏感)
     *
     * @param request HttpServletRequest
     * @param email   邮箱地址
     * @param captcha 验证码
     */
    public static boolean correctEmailCaptcha(HttpServletRequest request, String email, String captcha) {
        String sign = request.getHeader(RedisConstant.SIGN);
        String name = sign + RedisConstant.EMAIL_CAPTCHA_SUFFIX;
        Map<Object, Object> map = RedisUtils.hashGet(name);
        String redisEmail = (String) map.get(RedisConstant.EMAIL_CAPTCHA__EMAIL);
        String redisCaptcha = (String) map.get(RedisConstant.EMAIL_CAPTCHA__CAPTCHA);
        if (redisEmail == null || redisCaptcha == null) {
            return false;
        }
        if (!(redisEmail.equalsIgnoreCase(email) && redisCaptcha.equalsIgnoreCase(captcha))) {
            return false;
        }
        // 验证成功后一定要删除
        RedisUtils.delete(name);
        return true;
    }

}
