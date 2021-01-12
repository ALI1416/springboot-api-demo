package com.demo.dao;

import com.demo.entity.po.User;
import com.demo.entity.vo.UserVo;

import java.util.List;

/**
 * <h1>用户业务逻辑接口</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface UserDao {

    /**
     * 插入后或更新后备份
     */
    int bak(long id);

    /**
     * 注册
     */
    int register(User user);

    /**
     * 查询，通过id
     */
    User findById(Long id);

    /**
     * 查询，通过account
     */
    User findByAccount(String account);

    /**
     * 精确查询
     */
    List<User> findExact(User user);

    /**
     * 查询
     */
    List<User> find(UserVo user);

    /**
     * 更新
     */
    int updateById(User user);

    /**
     * 删除，通过id
     */
    int deleteById(long id);

}
