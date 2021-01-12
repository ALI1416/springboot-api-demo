package com.demo.dao;

import java.util.List;

import com.demo.entity.po.User;
import com.demo.entity.vo.UserVo;

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
     * 注册
     */
    int register(User user);

    /**
     * 注册备份
     */
    int registerBak(User user);

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
