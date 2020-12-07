package com.demo.dao;

import java.util.List;

import com.demo.po.User;
import com.demo.vo.UserVo;

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
