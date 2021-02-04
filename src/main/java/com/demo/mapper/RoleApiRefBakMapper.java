package com.demo.mapper;

import com.demo.entity.po.RoleApiRefBak;

/**
 * <h1>角色api引用备份Mapper</h1>
 *
 * <p>
 * createDate 2021/01/24 16:30:38
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface RoleApiRefBakMapper {

    /**
     * 插入后或更新后备份
     */
    int insert(RoleApiRefBak roleApiRefBak);
}
