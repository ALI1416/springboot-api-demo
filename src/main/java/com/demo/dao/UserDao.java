package com.demo.dao;

import com.demo.entity.User;
import com.demo.vo.UserVo;

import java.util.List;

/**
 * <h1>User业务逻辑接口层</h1>
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
     * 插入
     */
    int insert(User user);

    /**
     * 查询，通过id
     */
    User findById(Integer id);

    /**
     * 查询，通过account
     */
    User findByAccount(String account);

    /**
     * 查询所有
     */
    List<User> findAll();

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
    int deleteById(int id);

}
