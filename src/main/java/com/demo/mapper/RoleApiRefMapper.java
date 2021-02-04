package com.demo.mapper;

import com.demo.entity.vo.RoleApiRefVo;

import java.util.List;

/**
 * <h1>api角色引用Mapper</h1>
 *
 * <p>
 * createDate 2021/01/24 16:23:07
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface RoleApiRefMapper {

    /**
     * 新增(需id,name,createId)
     */
    int insert(RoleApiRefVo roleApiRef);

    /**
     * 存在一个唯一键(需其一id,name)
     */
    boolean existUniqueKey(RoleApiRefVo roleApiRef);

    /**
     * 查询一个唯一键(需其一id,name)
     */
    RoleApiRefVo findByUniqueKey(RoleApiRefVo roleApiRef);

    /**
     * 更新(需id,updateId;改不了id,createId,createTime,updateTime,version)
     */
    int updateById(RoleApiRefVo roleApiRef);

    /**
     * 精确查询
     */
    List<RoleApiRefVo> findExact(RoleApiRefVo roleApiRef);

}
