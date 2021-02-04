package com.demo.mapper;

import com.demo.entity.po.UserLoginLog;

/**
 * <h1>用户登录日志Mapper</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface UserLoginLogMapper {

    /**
     * 登录后记录日志
     */
    int insert(UserLoginLog userLoginLog);
}
