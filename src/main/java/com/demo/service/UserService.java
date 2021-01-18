package com.demo.service;

import com.demo.constant.ResultCodeEnum;
import com.demo.dao.UserBakDao;
import com.demo.dao.UserDao;
import com.demo.dao.UserLoginLogDao;
import com.demo.entity.po.User;
import com.demo.entity.po.UserBak;
import com.demo.entity.po.UserLoginLog;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.UserVo;
import com.demo.util.AuthUtils;
import com.demo.util.EncoderUtils;
import com.demo.util.RegexUtils;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <h1>用户服务</h1>
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
public class UserService extends BaseService {

    private final UserDao userDao;
    private final UserBakDao userBakDao;
    private final UserLoginLogDao userLoginLogDao;

    /**
     * 存在id
     */
    public Result existId(long id) {
        // 查找用户，通过id。用户不存在
        if (userDao.findById(id) == null) {
            return Result.e(ResultCodeEnum.USER_NOT_EXIST);
        }
        return Result.o();
    }

    /**
     * 存在account
     */
    public Result existAccount(String account) {
        // 查找用户，通过account。用户不存在
        if (userDao.findByAccount(account) == null) {
            return Result.e(ResultCodeEnum.USER_NOT_EXIST);
        }
        return Result.o();
    }

    /**
     * 存在email
     */
    public Result existEmail(String email) {
        // 查找用户，通过account。用户不存在
        if (userDao.findByEmail(email) == null) {
            return Result.e(ResultCodeEnum.EMAIL_NOT_EXIST);
        }
        return Result.o();
    }

    /**
     * 查找用户，通过id
     */
    public Result findById(long id) {
        // 查找用户，通过id
        User u = userDao.findById(id);
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCodeEnum.USER_NOT_EXIST);
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
            return Result.e(ResultCodeEnum.USER_NOT_EXIST);
        }
        return Result.o(u);
    }

    /**
     * 查找用户，通过email
     */
    public Result findByEmail(String email) {
        // 查找用户，通过email
        User u = userDao.findByEmail(email);
        // 用户不存在
        if (u == null) {
            return Result.e(ResultCodeEnum.EMAIL_NOT_EXIST);
        }
        return Result.o(u);
    }

    /**
     * 注册，通过account
     */
    @Transactional
    public Result register(HttpServletRequest request, User user) {
        // 插入
        Result result = tryif(() -> (userDao.register(user) == 1));
        // 失败
        if (!result.isOk()) {
            return result;
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        // redis放入userId
        AuthUtils.setUserId(request, user.getId());
        return Result.o();
    }

    /**
     * 注册，通过email
     */
    @Transactional
    public Result registerByEmail(HttpServletRequest request, UserVo user) {
        // 插入
        Result result = tryif(() -> (userDao.registerByEmail(user) == 1));
        // 失败
        if (!result.isOk()) {
            return result;
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        // redis放入userId
        AuthUtils.setUserId(request, user.getId());
        return Result.o();
    }

    /**
     * 登录
     */
    public Result login(HttpServletRequest request, User user) {
        User u;
        if (RegexUtils.isAccount(user.getAccount())) {
            // 查找用户，通过account
            u = userDao.findByAccount(user.getAccount());
        } else if (RegexUtils.isEmail(user.getAccount())) {
            // 查找用户，通过email
            u = userDao.findByEmail(user.getEmail());
        } else {
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        // 用户不存在或密码错误
        if (u == null || !EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            // 日志(登录失败)
            if (u == null) {
                recordLog(() -> userLoginLogDao.insert(new UserLoginLog(request, null, false)));
            } else {
                recordLog(() -> userLoginLogDao.insert(new UserLoginLog(request, u.getId(), false)));
            }
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        // 日志(登录成功)
        recordLog(() -> userLoginLogDao.insert(new UserLoginLog(request, u.getId(), true)));
        u.setPwd(null);
        // redis放入userId
        AuthUtils.setUserId(request, user.getId());
        return Result.o(u);
    }

    /**
     * 修改信息
     */
    @Transactional
    public Result changeInfo(User user) {
        // 不能修改密码
        user.setPwd(null);
        // 更新
        Result result = tryif(() -> (userDao.updateById(user) == 1));
        // 失败
        if (!result.isOk()) {
            return result;
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        return Result.o(user);
    }

    /**
     * 修改密码
     */
    @Transactional
    public Result changePwd(UserVo user) {
        // 查找用户
        User u = userDao.findById(user.getId());
        // 旧密码错误
        if (!EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        User u2 = new User();
        u2.setId(user.getId());
        u2.setPwd(EncoderUtils.bCrypt(user.getNewPwd()));
        // 更新
        Result result = tryif(() -> (userDao.updateById(u2) == 1));
        // 失败
        if (!result.isOk()) {
            return result;
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        return Result.o();
    }

    /**
     * 删除
     */
    @Transactional
    public Result deleteById(long id) {
        // 删除
        Result result = tryif(() -> (userDao.deleteById(id) == 1));
        // 失败
        if (!result.isOk()) {
            return result;
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(id)));
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
            Result ok = tryif(false, () -> (userDao.register(user) == 1));
            user.setPwd(null);
            result.add(ok.isOk(), user, ok.getMsg());
            if (ok.isOk()) {
                // 备份
                recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
            }
        }
        return Result.o(result);
    }

    /**
     * 精确查询
     */
    public Result findExact(User user) {
        PageInfo<User> pageInfo = pagination(user, () -> userDao.findExact(user));
        return Result.o(pageInfo);
    }

    /**
     * 模糊查询
     */
    public Result find(UserVo user) {
        PageInfo<User> pageInfo = pagination(user, () -> userDao.find(user));
        return Result.o(pageInfo);
    }

}
