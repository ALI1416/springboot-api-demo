package com.demo.entity.po;

import javax.servlet.http.HttpServletRequest;

import com.demo.entity.BaseEntity;
import com.demo.tool.Id;
import com.demo.util.ClientInfoUtils;
import com.demo.util.IpUtils;
import com.demo.util.UserAgentUtils;
import com.demo.util.pojo.IpInfo;
import com.demo.util.pojo.UserAgentInfo;

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
     * 登录类型：0、账号，1、邮箱，2、QQ
     * 
     * @see com.demo.constant.UserLoginTypeConstant
     */
    private Integer loginType;
    /**
     * 登录成功
     */
    private Integer loginSuccess;

    /**
     * 构造函数(自动生成id)
     */
    public UserLoginLog() {
        setId(Id.next());
    }

    /**
     * 登录成功构造函数(自动生成id)<br>
     * 设置ip信息<br>
     * 包括ip,ipCountry,ipProvince,ipCity<br>
     * 设置userAgent信息<br>
     * 包括userAgent,uaOsName,uaBrowserName,uaDeviceTypeName
     * 
     * @param request      HttpServletRequest
     * @param refId        refId
     * @param loginType    登录类型
     * @param loginSuccess 登录成功
     */
    public UserLoginLog(HttpServletRequest request, Long refId, Integer loginType, boolean loginSuccess) {
        setId(Id.next());
        setIpInfo(ClientInfoUtils.getIp(request));
        setUserAgentInfo(ClientInfoUtils.getUserAgent(request));
        setRefId(refId == null ? 0L : refId);
        setLoginType(loginType);
        setLoginSuccess(loginSuccess ? 1 : 0);
    }

    /**
     * 设置ip信息<br>
     * 包括ip,ipCountry,ipProvince,ipCity
     *
     * @param ipString IP地址
     */
    public void setIpInfo(String ipString) {
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
     * @param userAgentString UserAgent
     */
    public void setUserAgentInfo(String userAgentString) {
        UserAgentInfo userAgentInfo = UserAgentUtils.getUserAgentInfo(userAgentString);
        setUserAgent(userAgentString);
        setUaOsName(userAgentInfo.getOperatingSystemName());
        setUaBrowserName(userAgentInfo.getBrowserName());
        setUaDeviceTypeName(userAgentInfo.getDeviceTypeName());
    }
}
