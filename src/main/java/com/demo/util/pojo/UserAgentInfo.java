package com.demo.util.pojo;

import eu.bitwalker.useragentutils.UserAgent;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>UserAgentInfo实体</h1>
 *
 * <p>
 * createDate 2021/01/15 11:42:21
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class UserAgentInfo {

    /**
     * 操作系统名
     */
    private String operatingSystemName;
    /**
     * 浏览器名
     */
    private String browserName;
    /**
     * 设备类型名
     */
    private String deviceTypeName;

    /**
     * 构造函数
     */
    public UserAgentInfo() {

    }

    /**
     * 构造函数
     *
     * @param userAgent userAgent
     */
    public UserAgentInfo(UserAgent userAgent) {
        this.operatingSystemName = "Unknown".equals(userAgent.getOperatingSystem().getName()) //
                ? "" : userAgent.getOperatingSystem().getName();
        this.browserName = "Unknown".equals(userAgent.getBrowser().getName()) //
                ? "" : userAgent.getBrowser().getName();
        this.deviceTypeName = "Unknown".equals(userAgent.getOperatingSystem().getDeviceType().getName()) //
                ? "" : userAgent.getOperatingSystem().getDeviceType().getName();
    }

}
