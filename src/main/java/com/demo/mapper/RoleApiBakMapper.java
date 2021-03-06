package com.demo.mapper;

import com.demo.entity.po.RoleApiBak;

/**
 * <h1>角色api备份Mapper</h1>
 *
 * <p>
 * createDate 2021/01/24 16:28:59
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface RoleApiBakMapper {

    /**
     * 插入后或更新后备份
     */
    int insert(RoleApiBak roleApiBak);
}
