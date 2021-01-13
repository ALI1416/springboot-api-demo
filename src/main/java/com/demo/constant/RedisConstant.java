package com.demo.constant;

/**
 * <h1>TODO TokenConstant Title</h1>
 *
 * <p>
 * TODO TokenConstant Description
 * </p>
 *
 * <p>
 * createDate 2021/01/13 20:49:41
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
public class RedisConstant {
    /**
     * REDIS_ID名字{@value}
     */
    public static final String REDIS_ID_NAME = "redis_id";
    /**
     * REDIS过期时间(秒){@value}(7天)
     */
    public static final long REDIS_EXPIRE_TIME = 7 * 24 * 60 * 60;
    /**
     * TOKEN名字{@value}
     */
    public static final String TOKEN_NAME = "token";
    /**
     * TOKEN长度{@value}
     */
    public static final int TOKEN_LENGTH = 128;

    /**
     * QQ第三方登录时的STATE名字{@value}
     */
    public static final String QQ_STATE_NAME = "qq_state";


}
