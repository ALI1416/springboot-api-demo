package com.demo.entity.po;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户备份表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/14 16:23:13
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class UserBak extends User {

    /**
     * 用户id
     */
    private Long userId;

}
