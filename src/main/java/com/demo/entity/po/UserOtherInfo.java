package com.demo.entity.po;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户其他信息表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/14 16:33:29
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class UserOtherInfo {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 键
     */
    private String key;
    /**
     * 值
     */
    private String value;

}
