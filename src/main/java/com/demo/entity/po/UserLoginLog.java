package com.demo.entity.po;

import com.demo.entity.BaseEntity;
import com.demo.util.IpInfo;
import com.demo.util.IpUtils;

import eu.bitwalker.useragentutils.UserAgent;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户登录日志表持久化对象</h1>
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
public class UserLoginLog extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 登录成功
     */
    private Integer loginSuccess;
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

    /**
     * 设置ip信息<br>
     * 包括ip,ipCountry,ipProvince,ipCity
     *
     * @param ipString IP地址
     */
    public void setIpInfo(String ipString) {
        IpInfo ipInfo = IpUtils.getIpInfo(ipString);
        this.ip = ipString;
        this.ipCountry = ipInfo.getCountry();
        this.ipProvince = ipInfo.getProvince();
        this.ipCity = ipInfo.getCity();
    }

    /**
     * 设置UserAgent信息<br>
     * 包括userAgent,uaOsName,uaBrowserName,uaDeviceTypeName
     *
     * @param userAgentString UserAgent
     */
    public void setUserAgentInfo(String userAgentString) {
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        this.userAgent = userAgentString;
        this.uaOsName = userAgent.getOperatingSystem().getName();
        this.uaBrowserName = userAgent.getBrowser().getName();
        this.uaDeviceTypeName = userAgent.getOperatingSystem().getDeviceType().getName();
    }
}
