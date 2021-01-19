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
     * 注册，通过email
     */
    int registerByEmail(User user);

    /**
     * 存在id
     */
    boolean existId(long id);

    /**
     * 存在account
     */
    boolean existAccount(String account);

    /**
     * 存在email
     */
    boolean existEmail(String email);

    /**
     * 存在qqOpenid
     */
    boolean existQqOpenid(String qqOpenid);

    /**
     * 查询，通过id
     */
    User findById(long id);

    /**
     * 查询，通过account
     */
    User findByAccount(String account);

    /**
     * 查询，通过email
     */
    User findByEmail(String email);

    /**
     * 查询，通过qqOpenid
     */
    User findByQqOpenid(String qqOpenid);

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
