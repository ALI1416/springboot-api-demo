package com.demo.service;

import com.demo.constant.RedisConstant;
import com.demo.constant.ResultCodeEnum;
import com.demo.constant.UserLoginTypeEnum;
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
import com.demo.util.RedisUtils;
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

    /* ==================== 通用方法 ==================== */
//region
    /**
     * 存在id
     */
    public boolean existId(long id) {
        UserVo user = new UserVo();
        user.setId(id);
        return userDao.existUniqueKey(user);
    }

    /**
     * 存在account
     */
    public boolean existAccount(String account) {
        UserVo user = new UserVo();
        user.setAccount(account);
        return userDao.existUniqueKey(user);
    }

    /**
     * 存在email
     */
    public boolean existEmail(String email) {
        UserVo user = new UserVo();
        user.setEmail(email);
        return userDao.existUniqueKey(user);
    }

    /**
     * 存在qqOpenid
     */
    public boolean existQqOpenid(String qqOpenid) {
        UserVo user = new UserVo();
        user.setQqOpenid(qqOpenid);
        return userDao.existUniqueKey(user);
    }

    /**
     * 查找，通过id
     */
    public UserVo findById(long id) {
        UserVo user = new UserVo();
        user.setId(id);
        return userDao.findByUniqueKey(user);
    }

    /**
     * 查找，通过account
     */
    public UserVo findByAccount(String account) {
        UserVo user = new UserVo();
        user.setAccount(account);
        return userDao.findByUniqueKey(user);
    }

    /**
     * 查找account列表(需account)
     */
    public List<UserVo> findByAccountList(List<String> accountList) {
        return userDao.findByAccountList(accountList);
    }

    /**
     * 查找，通过email
     */
    public UserVo findByEmail(String email) {
        UserVo user = new UserVo();
        user.setEmail(email);
        return userDao.findByUniqueKey(user);
    }

    /**
     * 查找，通过qqOpenid
     */
    public UserVo findByQqOpenid(String qqOpenid) {
        UserVo user = new UserVo();
        user.setQqOpenid(qqOpenid);
        return userDao.findByUniqueKey(user);
    }
//endregion

    /* ==================== 客户端方法 ==================== */
//region
    /**
     * 注册，通过account(需id,account,pwd)
     */
    @Transactional
    public Result register(HttpServletRequest request, UserVo user) {
        // 插入失败
        if (!tryif(() -> userDao.register(user))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        // 日志(登录成功)
        recordLog(() -> userLoginLogDao.insert(//
                new UserLoginLog(request, user.getId(), UserLoginTypeEnum.ACCOUNT, true)));
        // redis放入userId
        AuthUtils.setUserId(request, user.getId());
        return Result.o();
    }

    /**
     * 注册，通过email(需id,email,pwd)
     */
    @Transactional
    public Result registerByEmail(HttpServletRequest request, UserVo user) {
        // 插入失败
        if (!tryif(() -> userDao.registerByEmail(user))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        // 日志(登录成功)
        recordLog(() -> userLoginLogDao.insert(//
                new UserLoginLog(request, user.getId(), UserLoginTypeEnum.EMAIL, true)));
        // redis放入userId
        AuthUtils.setUserId(request, user.getId());
        return Result.o();
    }

    /**
     * 注册，通过qq(需id,name,gender,year,qqName,qqOpenid)
     */
    @Transactional
    public Result registerByQq(HttpServletRequest request, String sign, UserVo user) {
        // 插入失败
        if (!tryif(() -> userDao.registerByQq(user))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        // 日志(登录成功)
        recordLog(() -> userLoginLogDao.insert(//
                new UserLoginLog(request, user.getId(), UserLoginTypeEnum.QQ, true)));
        // redis放入userId，并重置过期时间
        RedisUtils.hashSet(sign, RedisConstant._USER_ID, user.getId(), RedisConstant.EXPIRE);
        return Result.o();
    }

    /**
     * 登录(需account,pwd)
     */
    public Result login(HttpServletRequest request, UserVo user) {
        User u;
        UserLoginTypeEnum userLoginTypeEnum;
        if (RegexUtils.isAccount(user.getAccount())) {
            // 查找用户，通过account
            u = findByAccount(user.getAccount());
            userLoginTypeEnum = UserLoginTypeEnum.ACCOUNT;
        } else if (RegexUtils.isEmail(user.getAccount())) {
            // 查找用户，通过email
            u = findByEmail(user.getAccount());
            userLoginTypeEnum = UserLoginTypeEnum.EMAIL;
        } else {
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        // 用户不存在或账号禁用或密码错误
        if (u == null || u.getIsDelete() == 1 || !EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            // 日志(登录失败)
            if (u == null) {
                recordLog(() -> userLoginLogDao.insert(//
                        new UserLoginLog(request, null, userLoginTypeEnum, false)));
            } else {
                recordLog(() -> userLoginLogDao.insert(//
                        new UserLoginLog(request, u.getId(), userLoginTypeEnum, false)));
            }
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        // 日志(登录成功)
        recordLog(() -> userLoginLogDao.insert(//
                new UserLoginLog(request, u.getId(), userLoginTypeEnum, true)));
        // redis放入userId
        AuthUtils.setUserId(request, u.getId());
        return Result.o();
    }

    /**
     * 登录，通过qq(需sign,id,isDelete)
     */
    public Result loginByQq(HttpServletRequest request, String sign, UserVo user) {
        // 账号禁用
        if (user.getIsDelete() == 1) {
            // 日志(登录失败)
            recordLog(() -> userLoginLogDao.insert(//
                    new UserLoginLog(request, user.getId(), UserLoginTypeEnum.QQ, false)));
            return Result.e();
        }
        // 日志(登录成功)
        recordLog(() -> userLoginLogDao.insert(//
                new UserLoginLog(request, user.getId(), UserLoginTypeEnum.QQ, true)));
        // redis放入userId，并重置过期时间
        RedisUtils.hashSet(sign, RedisConstant._USER_ID, user.getId(), RedisConstant.EXPIRE);
        return Result.o();

    }

    /**
     * 修改信息(需id;至少一个account,pwd,name,gender,year,profile,comment,email,qqOpenid,qqName)
     */
    @Transactional
    public Result changeInfo(UserVo user) {
        // 更新失败
        if (!tryif(() -> userDao.update(user))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        return Result.o();
    }

    /**
     * 修改密码(需id,pwd,newPwd)
     */
    @Transactional
    public Result changePwd(UserVo user) {
        // 查找用户
        User u = findById(user.getId());
        // 旧密码错误
        if (!EncoderUtils.bCrypt(user.getPwd(), u.getPwd())) {
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        UserVo u2 = new UserVo();
        u2.setId(user.getId());
        u2.setPwd(EncoderUtils.bCrypt(user.getNewPwd()));
        // 更新失败
        if (!tryif(() -> userDao.update(u2))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(u2.getId())));
        return Result.o();
    }

    /**
     * 设置密码(需id,pwd)
     */
    @Transactional
    public Result setPwd(UserVo user) {
        // 查找用户
        User u = findById(user.getId());
        // 已设置密码
        if (!"".equals(u.getPwd())) {
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        UserVo u2 = new UserVo();
        u2.setId(user.getId());
        u2.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        // 更新失败
        if (!tryif(() -> userDao.update(u2))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(u2.getId())));
        return Result.o();
    }

    /**
     * 删除(需id)
     */
    @Transactional
    public Result deleteById(long id) {
        // 删除失败
        if (!tryif(() -> userDao.deleteById(id))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(id)));
        return Result.o();
    }
//endregion

    /* ==================== 管理端方法 ==================== */
//region

    /**
     * 插入(需id,account,pwd,createId)
     */
    @Transactional
    public Result insert(UserVo user) {
        // 插入
        if (!tryif(() -> userDao.insert(user))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
        return Result.o();
    }

    /**
     * 批量插入(需id,account,pwd,createId)
     */
    @Transactional
    public ResultBatch<String> batchInsert(List<UserVo> users) {
        ResultBatch<String> result = new ResultBatch<>();
        for (UserVo user : users) {
            // 插入
            if (tryif(false, () -> userDao.insert(user))) {
                result.add(true, user.getAccount(), "成功");
                // 备份
                recordBak(() -> userBakDao.insert(new UserBak(user.getId())));
            } else {
                result.add(false, user.getAccount(), "失败");
            }
        }
        return result;
    }

    /**
     * 精确查询
     */
    public Result findExact(UserVo user) {
        PageInfo<UserVo> pageInfo = pagination(user, () -> userDao.findExact(user));
        return Result.o(pageInfo);
    }

    /**
     * 模糊查询
     */
    public Result find(UserVo user) {
        PageInfo<UserVo> pageInfo = pagination(user, () -> userDao.find(user));
        return Result.o(pageInfo);
    }
//endregion
}
