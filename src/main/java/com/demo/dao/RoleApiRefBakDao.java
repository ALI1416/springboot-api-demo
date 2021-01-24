package com.demo.dao;

import com.demo.entity.po.RoleApiRefBak;

/**
 * <h1>角色api引用备份业务逻辑接口</h1>
 *
 * <p>
 * createDate 2021/01/24 16:30:38
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface RoleApiRefBakDao {

    /**
     * 插入后或更新后备份
     */
    int insert(RoleApiRefBak roleApiRefBak);
}
