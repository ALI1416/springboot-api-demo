package com.demo.mapper;

import java.util.List;

import com.demo.entity.User;
import com.demo.vo.UserVo;

/**
 * <h1>UserMapper接口层</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface UserMapper {

    int insert(User user);

    User findById(int id);

    List<User> findAll();

    List<User> findExact(User user);

    List<User> find(UserVo user);

    int updateById(User user);

    int deleteById(int id);

}
