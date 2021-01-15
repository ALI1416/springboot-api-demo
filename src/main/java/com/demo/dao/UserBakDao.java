package com.demo.dao;

import com.demo.entity.po.UserBak;

/**
 * <h1>用户备份业务逻辑接口</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface UserBakDao {

    /**
     * 插入后或更新后备份
     */
    int insert(UserBak userBak);
}
