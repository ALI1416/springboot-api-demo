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

    /* ==================== 默认 ==================== */
    /**
     * key在header中的名字{@value}<br>
     * 在redis中使用hash保存
     */
    public static final String SIGN = "sign";
    /**
     * key的过期时间(秒){@value}(7天)
     */
    public static final long EXPIRE = 7 * 24 * 60 * 60;
    /**
     * key中token字段名{@value}
     */
    public static final String _TOKEN = "token";
    /**
     * key中用户id字段名{@value}
     */
    public static final String _USER_ID = "userId";

    /* ==================== 普通验证码 ==================== */
    /**
     * 普通验证码key的中缀{@value}<br>
     * 在redis中使用string保存
     */
    public static final String CAPTCHA_INFIX = "_captcha_";

    /* ==================== 电子邮件验证码 ==================== */
    /**
     * 电子邮件验证码key的后缀{@value}<br>
     * 在redis中使用hash保存
     */
    public static final String EMAIL_CAPTCHA_SUFFIX = "_email_captcha";
    /**
     * 电子邮件验证码key的过期时间(秒){@value}(2小时)
     */
    public static final long EMAIL_CAPTCHA_EXPIRE = 2 * 60 * 60;
    /**
     * 电子邮件验证码key的邮件地址字段名{@value}
     */
    public static final String EMAIL_CAPTCHA__EMAIL = "email";
    /**
     * 电子邮件验证码key的验证码字段名{@value}
     */
    public static final String EMAIL_CAPTCHA__CAPTCHA = "captcha";

    /* ==================== QQ登录state验证 ==================== */
    /**
     * QQ登录state验证key的后缀{@value}<br>
     * 在redis中使用string保存
     */
    public static final String QQ_STATE_SUFFIX = "_qq_state";
    /**
     * QQ登录state验证key的过期时间(秒){@value}(10分钟)
     */
    public static final long QQ_STATE_EXPIRE = 10 * 60;

}
