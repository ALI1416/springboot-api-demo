package com.demo.entity.po;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户其他信息备份表持久化对象</h1>
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
public class UserOtherInfoBak extends UserOtherInfo {

    /**
     * 用户其他信息id
     */
    private Long userOtherInfoId;
}
