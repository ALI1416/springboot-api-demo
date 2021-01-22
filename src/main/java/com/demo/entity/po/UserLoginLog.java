package com.demo.entity.po;

import com.demo.constant.UserLoginTypeEnum;
import com.demo.entity.BaseEntity;
import com.demo.tool.Id;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

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
     * @see com.demo.constant.UserLoginTypeEnum
     */
    private Integer loginType;
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
     * @param request           HttpServletRequest
     * @param refId             refId
     * @param userLoginTypeEnum 用户登录类型枚举
     * @param loginSuccess      登录成功
     */
    public UserLoginLog(HttpServletRequest request, Long refId, UserLoginTypeEnum userLoginTypeEnum,
                        boolean loginSuccess) {
        setId(Id.next());
        setIpInfo(request);
        setUserAgentInfo(request);
        setRefId(refId == null ? 0L : refId);
        setLoginType(userLoginTypeEnum.getType());
        setLoginSuccess(loginSuccess ? 1 : 0);
    }

}
