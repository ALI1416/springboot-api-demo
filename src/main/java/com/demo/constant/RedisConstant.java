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
     * SIGN名字{@value}
     */
    public static final String SIGN_NAME = "sign";
    /**
     * 过期时间(秒){@value}(7天)
     */
    public static final long EXPIRE_TIME = 7 * 24 * 60 * 60;
    /**
     * TOKEN名字{@value}
     */
    public static final String TOKEN_NAME = "token";
    /**
     * TOKEN长度{@value}
     */
    public static final int TOKEN_LENGTH = 128;

    /* ==================== 验证码有关 ==================== */
    /**
     * 普通验证码中缀{@value}
     */
    public static final String CAPTCHA_INFIX = "_captcha_";
    /**
     * 电子邮件验证码后缀名{@value}
     */
    public static final String EMAIL_CAPTCHA_SUFFIX_NAME = "_captcha_email";
    /**
     * 电子邮件验证码过期时间(秒){@value}(2小时)
     */
    public static final long EMAIL_CAPTCHA_EXPIRE_TIME = 2 * 60 * 60;

    /* ==================== 用户有关 ==================== */
    /**
     * 用户id名字{@value}
     */
    public static final String USER_ID_NAME = "userId";
    /**
     * QQ第三方登录时的STATE名字{@value}
     */
    public static final String QQ_STATE_NAME = "qqState";


}
