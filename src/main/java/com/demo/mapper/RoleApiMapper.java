package com.demo.mapper;

import com.demo.entity.vo.RoleApiVo;

import java.util.List;

/**
 * <h1>api角色Mapper</h1>
 *
 * <p>
 * createDate 2021/01/24 15:48:39
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface RoleApiMapper {

    /**
     * 新增(需id,name,createId)
     */
    int insert(RoleApiVo roleApi);

    /**
     * 存在一个唯一键(仅一个id,name)
     */
    boolean existUniqueKey(RoleApiVo roleApi);

    /**
     * 查询一个唯一键(仅一个id,name)
     */
    RoleApiVo findByUniqueKey(RoleApiVo roleApi);

    /**
     * 更新(需id,updateId,name)
     */
    int updateById(RoleApiVo roleApi);

    /**
     * 删除(需id,updateId)
     */
    int deleteById(RoleApiVo roleApi);

    /**
     * 精确查询
     */
    List<RoleApiVo> findExact(RoleApiVo roleApi);

}
