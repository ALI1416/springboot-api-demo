package com.demo.constant;

import lombok.Getter;

/**
 * <h1>用户登录类型枚举</h1>
 *
 * <p>
 * createDate 2021/01/20 15:29:45
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
public enum UserLoginTypeEnum {

    /**
     * 账号0
     */
    ACCOUNT(0),
    /**
     * 邮箱1
     */
    EMAIL(1),
    /**
     * QQ2
     */
    QQ(2),
    ;

    /**
     * 类型
     */
    private final int type;

    /**
     * 构造函数
     *
     * @param type 类型码
     */
    UserLoginTypeEnum(int type) {
        this.type = type;
    }
}
