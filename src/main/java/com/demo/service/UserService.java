package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;

/**
 * <h1>User服务层</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public boolean exist(String account) {
        User u = new User();
        u.setAccount(account);
        List<User> us = userMapper.findExact(u);
        if (us.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public User inster(User user) {
        if (userMapper.insert(user) == 1) {
            return user;
        } else {
            return null;
        }
    }
}
