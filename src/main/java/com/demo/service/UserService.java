package com.demo.service;

import com.demo.constant.ResultCodeEnum;
import com.demo.dao.UserDao;
import com.demo.entity.po.User;
import com.demo.entity.po.UserLog;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.UserVo;
import com.demo.util.ClientInfoUtils;
import com.demo.util.EncoderUtils;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
public class UserService extends BaseService {

    private final UserDao userDao;

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
     * 注册
     */
    @Transactional
    public Result register(User user) {
        // 查找用户，通过account。用户已存在
        if (userDao.findByAccount(user.getAccount()) != null) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        // 插入
        Result result = tryif(() -> (userDao.register(user) == 1));
        // 失败
        if (!result.isOk()) {
            return result;
        }
        // 备份
        recordBak(() -> userDao.bak(user.getId()));
        user.setPwd(null);
        return Result.o(user);
    }

    /**
     * 登录
     */
    public Result login(User user, HttpServletRequest request) {
        // 查找用户，通过account
        User u = userDao.findByAccount(user.getAccount());
        // 用户不存在或密码错误
        if (u == null || !EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        u.setPwd(null);
        // 日志
        recordLog(() -> {
            String ipString = ClientInfoUtils.getIp(request);
            String userAgentString = ClientInfoUtils.getUserAgent(request);
            UserLog userLog = new UserLog();
            userLog.setId(u.getId());
            userLog.setIpInfo(ipString);
            userLog.setUserAgentInfo(userAgentString);
            return userDao.log(userLog);
        });
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
            return Result.e(ResultCodeEnum.USER_NOT_EXIST);
        }
        // 修改account
        if (user.getAccount() != null) {
            // 要修改的account和以前的需要一样
            if (!user.getAccount().equals(u.getAccount())) {
                // 查找用户，通过account
                if (userDao.findByAccount(user.getAccount()) != null) {
                    // 用户已存在
                    return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
                }
            } else {
                user.setAccount(null);
            }
        }
        // 不能修改密码
        user.setPwd(null);
        // 更新
        Result result = tryif(() -> (userDao.updateById(user) == 1));
        // 失败
        if (!result.isOk()) {
            return result;
        }
        // 备份
        recordBak(() -> userDao.bak(user.getId()));
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
            return Result.e(ResultCodeEnum.USER_NOT_EXIST);
        }
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
        recordBak(() -> userDao.bak(u.getId()));
        return Result.o();
    }

    /**
     * 删除
     */
    @Transactional
    public Result deleteById(long id) {
        // 查找用户，通过id。用户不存在
        if (userDao.findById(id) == null) {
            return Result.e(ResultCodeEnum.USER_NOT_EXIST);
        }
        // 删除
        Result result = tryif(() -> (userDao.deleteById(id) == 1));
        // 失败
        if (!result.isOk()) {
            return result;
        }
        // 备份
        recordBak(() -> userDao.bak(id));
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
                recordBak(() -> userDao.bak(user.getId()));
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
