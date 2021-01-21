package com.demo.constant;

import lombok.Getter;

/**
 * <h1>第三方登录类型枚举</h1>
 *
 * <p>
 * createDate 2021/01/20 15:29:45
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
public enum ThirdLoginTypeEnum {

    /**
     * 账号未登录时，通过第三方登录/注册0
     */
    LOGIN(0),
    /**
     * 账号已登录时，通过第三方绑定/解绑1
     */
    BIND(1);

    /**
     * 类型
     */
    private final int type;

    /**
     * 构造函数
     * 
     * @param type 类型码
     */
    ThirdLoginTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 寻找ThirdLoginTypeEnum通过类型
     *
     * @param type 类型
     * @return 找不到返回null
     */
    public static ThirdLoginTypeEnum findByType(int type) {
        for (ThirdLoginTypeEnum item : ThirdLoginTypeEnum.values()) {
            if (type == item.getType()) {
                return item;
            }
        }
        return null;
    }

}
