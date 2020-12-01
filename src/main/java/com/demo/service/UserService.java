package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.exception.SimplifyException;
import com.demo.result.BatchResult;
import com.demo.result.Result;
import com.demo.result.ResultCode;
import com.demo.util.EncoderUtils;
import com.demo.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private UserDao userDao;

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
        if (ok.ok()) {
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
     * 修改信息（除id和密码）
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
        if (ok.ok()) {
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
        if (ok.ok()) {
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
        if (ok.ok()) {
            return ok;
        }
        return Result.o();
    }

    /**
     * 批量插入
     */
    @Transactional
    public Result batchRegister(List<User> list) {
        BatchResult<User> result = new BatchResult<>();
        for (User user : list) {
            // 插入
            Result ok = SimplifyException.tryif(false, () -> (userDao.insert(user) == 1));
            result.add(ok.ok(), user);
        }
        return Result.o(result);
    }

    /**
     * 查找全部
     * 
     * @param pages   页码
     * @param rows    每页条数(为0时查询全部)
     * @param orderBy 排序(为null时默认排序)
     */
    public Result findAll(int pages, int rows, String orderBy) {
        // orderBy == null && rows == 0：查询全部，默认排序
        if (orderBy == null) {
            if (rows != 0) {
                // 分页查询，默认排序
                PageHelper.startPage(pages, rows);
            }
        } else {
            if (rows == 0) {
                // 查询全部，排序
                PageHelper.orderBy(orderBy);
            } else {
                // 分页查询，排序
                PageHelper.startPage(pages, rows, orderBy);
            }
        }
        List<User> user = userDao.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(user);
        return Result.o(pageInfo);
    }

}
