package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.UserDao;
import com.demo.entity.User;
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

    private final UserDao USER;

    /**
     * 存在id
     */
    public Result existId(int id) {
        // 查找用户，通过id。用户不存在
        if (USER.findById(id) == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        return Result.ok();
    }

    /**
     * 存在account
     */
    public Result existAccount(String account) {
        // 查找用户，通过account。用户不存在
        if (USER.findByAccount(account) == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        return Result.ok();
    }

    /**
     * 查找用户，通过id
     */
    public Result findById(int id) {
        // 查找用户，通过id
        User u = USER.findById(id);
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        return Result.ok(u);
    }

    /**
     * 查找用户，通过account
     */
    public Result findByAccount(String account) {
        // 查找用户，通过account
        User u = USER.findByAccount(account);
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        return Result.ok(u);
    }

    /**
     * 注册
     */
    @Transactional
    public Result register(User user) {
        // 查找用户，通过account。用户不存在
        if (USER.findByAccount(user.getAccount()) != null) {
            return Result.e(ResultCode.USER_HAS_EXISTED);
        }
        // 插入账号。插入失败
        if (USER.insert(user) != 1) {
            return Result.e2();
        }
        user.setPwd(null);
        return Result.ok(user);
    }

    /**
     * 登录
     */
    @Transactional
    public Result login(User user) {
        // 查找用户，通过account
        User u = USER.findByAccount(user.getAccount());
        // 用户不存在或密码错误
        if (u == null || !EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            return Result.e(ResultCode.USER_LOGIN_ERROR);
        }
        u.setPwd(null);
        return Result.ok(u);
    }

    /**
     * 修改信息（除id和密码）
     */
    @Transactional
    public Result changeInfo(User user) {
        // 查找用户，通过id
        User u = USER.findById(user.getId());
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        // 查找用户，通过account。用户存在（修改account）
        if (USER.findByAccount(user.getAccount()) != null) {
            return Result.e(ResultCode.USER_HAS_EXISTED);
        }
        user.setPwd(null);
        // 更新。更新失败
        if (USER.updateById(user) != 1) {
            return Result.e2();
        }
        return Result.ok(user);
    }

    /**
     * 修改密码
     */
    @Transactional
    public Result changePwd(UserVo user) {
        // 查找用户，通过id
        User u = USER.findById(user.getId());
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        // 旧密码错误
        if (!EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            return Result.e(ResultCode.USER_LOGIN_ERROR);
        }
        User u2 = new User();
        u2.setId(user.getId());
        u2.setPwd(EncoderUtils.bCrypt(user.getNewPwd()));
        // 更新。更新失败
        if (USER.updateById(u2) != 1) {
            return Result.e2();
        }
        return Result.ok();
    }

    /**
     * 删除
     */
    @Transactional
    public Result deleteById(int id) {
        // 查找用户，通过id。用户不存在
        if (USER.findById(id) == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        // 删除。删除失败
        if (USER.deleteById(id) != 1) {
            return Result.e2();
        }
        return Result.ok();
    }

}
