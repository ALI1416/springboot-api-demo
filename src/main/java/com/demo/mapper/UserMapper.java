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

    public int insert(User user);

    public User findById(int id);

    public List<User> findAll();

    public List<User> findExact(User user);

    public List<User> find(UserVo user);

    public int updateById(User user);

    public int deleteById(int id);

}
