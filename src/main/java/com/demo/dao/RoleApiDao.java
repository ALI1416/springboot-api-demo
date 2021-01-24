package com.demo.dao;

import com.demo.entity.vo.RoleApiVo;

import java.util.List;

/**
 * <h1>api角色业务逻辑接口</h1>
 *
 * <p>
 * createDate 2021/01/24 15:48:39
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface RoleApiDao {

    /**
     * 新增(需id,name,createId)
     */
    int insert(RoleApiVo roleApi);

    /**
     * 存在一个唯一键(需其一id,name)
     */
    boolean existUniqueKey(RoleApiVo roleApi);

    /**
     * 查询一个唯一键(需其一id,name)
     */
    RoleApiVo findByUniqueKey(RoleApiVo roleApi);

    /**
     * 更新(需id,updateId;改不了id,createId,createTime,updateTime,version)
     */
    int updateById(RoleApiVo roleApi);

    /**
     * 精确查询
     */
    List<RoleApiVo> findExact(RoleApiVo roleApi);

}
