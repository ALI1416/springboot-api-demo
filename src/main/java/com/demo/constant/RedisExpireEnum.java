package com.demo.constant;

import java.util.concurrent.TimeUnit;

/**
 * <h1>redis过期时间相关枚举</h1>
 *
 * <p>
 * createDate 2020/12/4 15:57:36
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public enum RedisExpireEnum {
    /**
     * 未读消息的有效期为30天
     */
    UNREAD_MSG(30L, TimeUnit.DAYS);

    /**
     * 过期时间
     */
    private final Long time;
    /**
     * 时间单位
     */
    private final TimeUnit timeUnit;

    RedisExpireEnum(Long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public Long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
