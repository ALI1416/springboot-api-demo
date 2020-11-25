package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.result.Result;
import com.demo.result.ResultCode;
import com.demo.util.EncoderUtils;
import com.demo.vo.UserVo;

import lombok.AllArgsConstructor;

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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserMapper USER;

    /**
     * 是否存在该账号
     */
    public Result existAccount(String account) {
        // 查找用户，通过account
        if (USER.findByAccount(account) != null) {
            return Result.e(ResultCode.USER_HAS_EXISTED);
        }
        return Result.ok();
    }

    /**
     * 查找用户，通过id
     */
    public Result findById(int id) {
        // 查找用户，通过id
        User u = USER.findById(id);
        if (u == null) {
            return Result.e(ResultCode.USER_LOGIN_ERROR);
        }
        return Result.ok(u);
    }

    /**
     * 查找用户，通过account
     */
    public Result findByAccount(String account) {
        // 查找用户，通过account
        User u = USER.findByAccount(account);
        if (u == null) {
            return Result.e(ResultCode.USER_LOGIN_ERROR);
        }
        return Result.ok(u);
    }

    /**
     * 注册
     */
    @Transactional
    public Result register(User user) {
        // 查找账号是否存在
        Result r = existAccount(user.getAccount());
        if (r.getCODE() != 0) {
            return r;
        }
        // 插入账号
        if (USER.insert(user) != 1) {
            return Result.e(ResultCode.USER_HAS_EXISTED);
        }
        user.setPwd(null);
        return Result.ok(user);
    }

    /**
     * 登录
     */
    @Transactional
    public Result login(User user) {
        // 查找账号
        Result r = findByAccount(user.getAccount());
        // 账号不存在
        if (r.getCODE() != 0) {
            return r;
        }
        User u = (User) r.getDATA();
        // 密码错误
        if (!EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            return Result.e(ResultCode.USER_LOGIN_ERROR);
        }
        u.setPwd(null);
        return Result.ok(u);
    }

    /**
     * 修改信息
     */
    @Transactional
    public Result changeInfo(User user) {
        // 更新
        int n = USER.updateById(user);
        if (n != 1) {
            return Result.e(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.ok(user);
    }

    /**
     * 修改密码
     */
    @Transactional
    public Result changePwd(UserVo user) {
        // 登录
        Result r = login(user);
        if (r.getCODE() != 0) {
            return r;
        }
        user.setPwd(user.getNewPwd());
        // 修改信息
        Result r2 = changeInfo(user);
        if (r2.getCODE() != 0) {
            return r2;
        }
        return Result.ok();
    }
}
