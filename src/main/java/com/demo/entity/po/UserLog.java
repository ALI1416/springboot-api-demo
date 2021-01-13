package com.demo.entity.po;

import com.demo.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>UserLog持久对象</h1>
 *
 * <p>
 * createDate 2021/01/13 16:14:40
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class UserLog extends BaseEntity {

    /**
     * IP地址
     */
    private String ip;
    /**
     * 浏览器标识
     */
    private String userAgent;
    /**
     * IP地址-国家
     */
    private String ipCountry;
    /**
     * IP地址-省份
     */
    private String ipProvince;
    /**
     * IP地址-城市
     */
    private String ipCity;
    /**
     * 浏览器标识-操作系统名
     */
    private String uaOsName;
    /**
     * 浏览器标识-浏览器名
     */
    private String uaBrowserName;
    /**
     * 浏览器标识-设备类型名
     */
    private String uaDeviceTypeName;
}
