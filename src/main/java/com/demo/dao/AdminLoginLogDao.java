package com.demo.dao;

import com.demo.entity.po.AdminLoginLog;

/**
 * <h1>管理员登录日志业务逻辑接口</h1>
 *
 * <p>
 * createDate 2021/01/21 21:55:20
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface AdminLoginLogDao {

    /**
     * 登录后记录日志
     */
    int insert(AdminLoginLog adminLoginLog);
}
