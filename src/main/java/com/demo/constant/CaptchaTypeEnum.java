package com.demo.constant;

import lombok.Getter;

/**
 * <h1>图片验证码类型枚举</h1>
 *
 * <p>
 * 防止一个页面出现多个图片验证码，产生冲突
 * </p>
 * <p>
 * createDate 2021/01/17 15:35:55
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
public enum CaptchaTypeEnum {

    /**
     * 默认：5分钟
     */
    DEFAULT(0, 5 * 60),
    /**
     * 电子邮件：5分钟
     */
    EMAIL(1, 5 * 60);

    /**
     * 类型
     */
    private final int type;
    /**
     * 过期时间(秒)
     */
    private final long expire;

    /**
     * 构造函数
     *
     * @param type   类型码
     * @param expire 过期时间
     */
    CaptchaTypeEnum(int type, long expire) {
        this.type = type;
        this.expire = expire;
    }

    /**
     * 寻找CaptchaTypeEnum通过类型
     *
     * @param type 类型
     * @return 找不到返回null
     */
    public static CaptchaTypeEnum findByType(int type) {
        for (CaptchaTypeEnum item : CaptchaTypeEnum.values()) {
            if (type == item.getType()) {
                return item;
            }
        }
        return null;
    }
}
