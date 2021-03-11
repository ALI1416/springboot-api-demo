package com.demo.entity.po;

import cn.z.id.Id;
import com.demo.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>管理员登录日志表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:06:33
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class AdminLoginLog extends BaseEntity {

    /**
     * 登录成功
     */
    private Integer loginSuccess;

    /**
     * 构造函数(自动生成id)<br>
     * 设置ip信息<br>
     * 包括ip,ipCountry,ipProvince,ipCity<br>
     * 设置userAgent信息<br>
     * 包括userAgent,uaOsName,uaBrowserName,uaDeviceTypeName
     *
     * @param request      HttpServletRequest
     * @param refId        refId
     * @param loginSuccess 登录成功
     */
    public AdminLoginLog(HttpServletRequest request, Long refId, boolean loginSuccess) {
        setId(Id.next());
        setIpInfo(request);
        setUserAgentInfo(request);
        setRefId(refId == null ? 0L : refId);
        setLoginSuccess(loginSuccess ? 1 : 0);
    }

}
