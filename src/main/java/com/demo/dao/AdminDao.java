package com.demo.dao;

import com.demo.entity.vo.AdminVo;

import java.util.List;

/**
 * <h1>管理员业务逻辑接口</h1>
 *
 * <p>
 * createDate 2021/01/21 21:46:56
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface AdminDao {

    /**
     * 新增(需id,account,pwd,createId)
     */
    int insert(AdminVo admin);

    /**
     * 存在一个唯一键(仅一个id,account)
     */
    boolean existUniqueKey(AdminVo admin);

    /**
     * 查询一个唯一键(仅一个id,account)
     */
    AdminVo findByUniqueKey(AdminVo admin);

    /**
     * 更新(需id;至少一个account,pwd,name,comment)
     */
    int updateById(AdminVo admin);
    
    /**
     * 删除(需id)
     */
    int deleteById(AdminVo admin);

    /**
     * 精确查询
     */
    List<AdminVo> findExact(AdminVo admin);

}
