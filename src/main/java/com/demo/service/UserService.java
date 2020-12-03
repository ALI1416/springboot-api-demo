package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.tool.*;
import com.demo.util.EncoderUtils;
import com.demo.vo.UserVo;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h1>User服务</h1>
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

    private final UserDao userDao;

    /**
     * 存在id
     */
    public Result existId(int id) {
        // 查找用户，通过id。用户不存在
        if (userDao.findById(id) == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        return Result.o();
    }

    /**
     * 存在account
     */
    public Result existAccount(String account) {
        // 查找用户，通过account。用户不存在
        if (userDao.findByAccount(account) == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        return Result.o();
    }

    /**
     * 查找用户，通过id
     */
    public Result findById(int id) {
        // 查找用户，通过id
        User u = userDao.findById(id);
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        return Result.o(u);
    }

    /**
     * 查找用户，通过account
     */
    public Result findByAccount(String account) {
        // 查找用户，通过account
        User u = userDao.findByAccount(account);
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        return Result.o(u);
    }

    /**
     * 注册
     */
    @Transactional
    public Result register(User user) {
        // 查找用户，通过account。用户不存在
        if (userDao.findByAccount(user.getAccount()) != null) {
            return Result.e(ResultCode.USER_HAS_EXISTED);
        }
        // 插入
        Result ok = SimplifyException.tryif(() -> (userDao.insert(user) == 1));
        if (ok.isOk()) {
            return ok;
        }
        user.setPwd(null);
        return Result.o(user);
    }

    /**
     * 登录
     */
    public Result login(User user) {
        // 查找用户，通过account
        User u = userDao.findByAccount(user.getAccount());
        // 用户不存在或密码错误
        if (u == null || !EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            return Result.e(ResultCode.USER_LOGIN_ERROR);
        }
        u.setPwd(null);
        return Result.o(u);
    }

    /**
     * 修改信息
     */
    @Transactional
    public Result changeInfo(User user) {
        // 查找用户，通过id
        User u = userDao.findById(user.getId());
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        // 查找用户，通过account。用户存在（修改account）
        if (userDao.findByAccount(user.getAccount()) != null) {
            return Result.e(ResultCode.USER_HAS_EXISTED);
        }
        user.setPwd(null);
        // 更新
        Result ok = SimplifyException.tryif(() -> (userDao.updateById(user) == 1));
        if (ok.isOk()) {
            return ok;
        }
        return Result.o(user);
    }

    /**
     * 修改密码
     */
    @Transactional
    public Result changePwd(UserVo user) {
        // 查找用户，通过id
        User u = userDao.findById(user.getId());
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
        // 更新
        Result ok = SimplifyException.tryif(() -> (userDao.updateById(u2) == 1));
        if (ok.isOk()) {
            return ok;
        }
        return Result.o();
    }

    /**
     * 删除
     */
    @Transactional
    public Result deleteById(int id) {
        // 查找用户，通过id。用户不存在
        if (userDao.findById(id) == null) {
            return Result.e(ResultCode.USER_NOT_EXIST);
        }
        // 删除
        Result ok = SimplifyException.tryif(() -> (userDao.deleteById(id) == 1));
        if (ok.isOk()) {
            return ok;
        }
        return Result.o();
    }

    /**
     * 批量插入
     */
    @Transactional
    public Result batchRegister(List<User> list) {
        ResultBatch<User> result = new ResultBatch<>();
        for (User user : list) {
            // 插入
            Result ok = SimplifyException.tryif(false, () -> (userDao.insert(user) == 1));
            user.setPwd(null);
            result.add(ok.isOk(), user);
        }
        return Result.o(result);
    }

    /**
     * 查询全部
     */
    public Result findAll(int pages, int rows, String orderBy) {
        PageInfo<User> pageInfo = SimplifyPageHelper.pagination(pages, rows, orderBy, userDao::findAll);
        return Result.o(pageInfo);
    }

    /**
     * 精确查询
     */
    public Result findExact(User user, int pages, int rows, String orderBy) {
        PageInfo<User> pageInfo = SimplifyPageHelper.pagination(pages, rows, orderBy, () -> userDao.findExact(user));
        return Result.o(pageInfo);
    }

    /**
     * 模糊查询
     */
    public Result find(UserVo user, int pages, int rows, String orderBy) {
        PageInfo<User> pageInfo = SimplifyPageHelper.pagination(pages, rows, orderBy, () -> userDao.find(user));
        return Result.o(pageInfo);
    }

}
