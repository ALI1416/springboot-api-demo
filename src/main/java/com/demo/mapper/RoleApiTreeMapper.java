package com.demo.mapper;

import com.demo.entity.vo.RoleApiTreeVo;

import java.util.List;

/**
 * <h1>api角色树Mapper</h1>
 *
 * <p>
 * createDate 2021/01/24 16:23:19
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface RoleApiTreeMapper {

    /**
     * 新增(需id,name,createId)
     */
    int insert(RoleApiTreeVo roleApiTree);

    /**
     * 存在一个唯一键(需其一id,name)
     */
    boolean existUniqueKey(RoleApiTreeVo roleApiTree);

    /**
     * 查询一个唯一键(需其一id,name)
     */
    RoleApiTreeVo findByUniqueKey(RoleApiTreeVo roleApiTree);

    /**
     * 更新(需id,updateId;改不了id,createId,createTime,updateTime,version)
     */
    int updateById(RoleApiTreeVo roleApiTree);

    /**
     * 精确查询
     */
    List<RoleApiTreeVo> findExact(RoleApiTreeVo roleApiTree);

}
