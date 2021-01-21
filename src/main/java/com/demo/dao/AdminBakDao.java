package com.demo.dao;

import com.demo.entity.po.AdminBak;

/**
 * <h1>管理员备份业务逻辑接口</h1>
 *
 * <p>
 * createDate 2021/01/21 21:54:28
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface AdminBakDao {

    /**
     * 插入后或更新后备份
     */
    int insert(AdminBak adminBak);
}
