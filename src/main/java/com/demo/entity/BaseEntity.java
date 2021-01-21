package com.demo.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.util.ClientInfoUtils;
import com.demo.util.IpUtils;
import com.demo.util.UserAgentUtils;
import com.demo.util.pojo.IpInfo;
import com.demo.util.pojo.UserAgentInfo;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * <h1>基实体</h1>
 *
 * <p>
 * createDate 2020/11/22 15:43:28
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class BaseEntity {

    /* ++++++++++++++++++++ 属性 ++++++++++++++++++++ */
    /* ==================== po ==================== */
    /* -------------------- 所有表 -------------------- */
    /**
     * id
     */
    private Long id;
    /* -------------------- 大多数表 -------------------- */
    /**
     * 已删除
     */
    private Integer isDelete;
    /**
     * 创建者id
     */
    private Long createId;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新者id
     */
    private Long updateId;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 版本
     */
    private Integer version;
    /* -------------------- 备份、日志表 -------------------- */
    /**
     * 被备份的id/被登录的id
     */
    private Long refId;
    /* -------------------- 日志表 -------------------- */
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

    /* ==================== vo ==================== */
    /* -------------------- 分页 -------------------- */
    /**
     * 分页-页码
     */
    private Integer pages;
    /**
     * 分页-每页条数
     */
    private Integer rows;
    /**
     * 分页-排序
     */
    private String orderBy;

    /* ++++++++++++++++++++ 方法 ++++++++++++++++++++ */
    /* ==================== 日志类 ==================== */

    /**
     * 设置ip信息<br>
     * 包括ip,ipCountry,ipProvince,ipCity
     *
     * @param request HttpServletRequest
     */
    public void setIpInfo(HttpServletRequest request) {
        String ipString = ClientInfoUtils.getIp(request);
        IpInfo ipInfo = IpUtils.getIpInfo(ipString);
        setIp(ipString);
        setIpCountry(ipInfo.getCountry());
        setIpProvince(ipInfo.getProvince());
        setIpCity(ipInfo.getCity());
    }

    /**
     * 设置userAgent信息<br>
     * 包括userAgent,uaOsName,uaBrowserName,uaDeviceTypeName
     *
     * @param request HttpServletRequest
     */
    public void setUserAgentInfo(HttpServletRequest request) {
        String userAgentString = ClientInfoUtils.getUserAgent(request);
        UserAgentInfo userAgentInfo = UserAgentUtils.getUserAgentInfo(userAgentString);
        setUserAgent(userAgentString);
        setUaOsName(userAgentInfo.getOperatingSystemName());
        setUaBrowserName(userAgentInfo.getBrowserName());
        setUaDeviceTypeName(userAgentInfo.getDeviceTypeName());
    }

    /* ==================== 所有类 ==================== */

    /**
     * 重写toString成JSON格式
     */
    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(//
                this, //
                "yyyy-MM-dd HH:mm:ss", // 日期格式化样式
                SerializerFeature.DisableCircularReferenceDetect, // 禁用对象循环引用：避免$ref
                SerializerFeature.WriteNonStringValueAsString// 非String转为String：防止long丢失精度
        );
    }

}
