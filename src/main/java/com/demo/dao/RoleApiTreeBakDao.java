package com.demo.dao;

import com.demo.entity.po.RoleApiTreeBak;

/**
 * <h1>角色api树备份业务逻辑接口</h1>
 *
 * <p>
 * createDate 2021/01/24 16:31:02
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface RoleApiTreeBakDao {

    /**
     * 插入后或更新后备份
     */
    int insert(RoleApiTreeBak roleApiTreeBak);
}
