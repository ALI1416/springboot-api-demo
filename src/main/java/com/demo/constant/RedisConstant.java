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
    public final static String SIGN = "sign";
    /**
     * key的过期时间(秒){@value}(7天)
     */
    public final static long EXPIRE = 7 * 24 * 60 * 60;
    /**
     * key中token字段名{@value}
     */
    public final static String _TOKEN = "token";
    /**
     * key中用户id字段名{@value}
     */
    public final static String _USER_ID = "userId";

    /* ==================== 普通验证码 ==================== */
    /**
     * 普通验证码key的中缀{@value}<br>
     * 在redis中使用string保存
     */
    public final static String CAPTCHA_INFIX = "_captcha_";

    /* ==================== 电子邮件验证码 ==================== */
    /**
     * 电子邮件验证码key的后缀{@value}<br>
     * 在redis中使用hash保存
     */
    public final static String EMAIL_CAPTCHA_SUFFIX = "_email_captcha";
    /**
     * 电子邮件验证码key的过期时间(秒){@value}(2小时)
     */
    public final static long EMAIL_CAPTCHA_EXPIRE = 2 * 60 * 60;
    /**
     * 电子邮件验证码key的邮件地址字段名{@value}
     */
    public final static String EMAIL_CAPTCHA__EMAIL = "email";
    /**
     * 电子邮件验证码key的验证码字段名{@value}
     */
    public final static String EMAIL_CAPTCHA__CAPTCHA = "captcha";

    /* ==================== 第三方QQ登录验证 ==================== */
    /**
     * 第三方QQ登录验证key的后缀{@value}<br>
     * 在redis中使用hash保存
     */
    public final static String QQ_STATE_SUFFIX = "_third_qq";
    /**
     * 第三方QQ登录验证key的过期时间(秒){@value}(10分钟)
     */
    public final static long QQ_STATE_EXPIRE = 10 * 60;
    /**
     * 第三方QQ登录验证key的state字段名{@value}
     */
    public final static String QQ_STATE__STATE = "state";
    /**
     * 第三方QQ登录验证key的类型字段名{@value}
     */
    public final static String QQ_STATE__TYPE = "type";

}
