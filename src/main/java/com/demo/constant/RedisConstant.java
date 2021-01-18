package com.demo.constant;

/**
 * <h1>Redis常量</h1>
 *
 * <p>
 * createDate 2021/01/13 20:49:41
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class RedisConstant {

    /* ==================== 权限认证有关 ==================== */
    /**
     * key在header中的名字{@value}
     */
    public static final String SIGN_NAME = "sign";
    /**
     * key的过期时间(秒){@value}(7天)
     */
    public static final long EXPIRE_TIME = 7 * 24 * 60 * 60;
    /**
     * key中token字段的名字{@value}
     */
    public static final String TOKEN_NAME = "token";
    /**
     * key中token字段的长度{@value}
     */
    public static final int TOKEN_LENGTH = 128;

    /* ==================== 验证码有关 ==================== */
    /**
     * 普通验证码key的中缀{@value}
     */
    public static final String CAPTCHA_INFIX = "_captcha_";
    /**
     * 电子邮件验证码key的后缀{@value}
     */
    public static final String EMAIL_CAPTCHA_SUFFIX_NAME = "_captcha_email";
    /**
     * 电子邮件验证码key的过期时间(秒){@value}(2小时)
     */
    public static final long EMAIL_CAPTCHA_EXPIRE_TIME = 2 * 60 * 60;
    /**
     * 电子邮件验证码key的邮件地址字段名{@value}
     */
    public static final String EMAIL_CAPTCHA_EMAIL_NAME = "email";
    /**
     * 电子邮件验证码key的验证码字段名{@value}
     */
    public static final String EMAIL_CAPTCHA_CAPTCHA_NAME = "captcha";

    /* ==================== 用户有关 ==================== */
    /**
     * key中用户id字段名{@value}
     */
    public static final String USER_ID_NAME = "userId";
    /**
     * key中QQ第三方登录时的state的字段名{@value}
     */
    public static final String QQ_STATE_NAME = "qqState";


}
