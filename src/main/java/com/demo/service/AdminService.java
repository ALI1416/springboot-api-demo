package com.demo.service;

import com.demo.constant.ResultCodeEnum;
import com.demo.dao.AdminBakDao;
import com.demo.dao.AdminDao;
import com.demo.dao.AdminLoginLogDao;
import com.demo.entity.po.Admin;
import com.demo.entity.po.AdminBak;
import com.demo.entity.po.AdminLoginLog;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.AdminVo;
import com.demo.util.AuthUtils;
import com.demo.util.EncoderUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>管理员服务</h1>
 *
 * <p>
 * createDate 2021/01/23 19:13:01
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminService extends BaseService {

    private final AdminDao adminDao;
    private final AdminBakDao adminBakDao;
    private final AdminLoginLogDao adminLoginLogDao;

    /**
     * 存在id
     */
    public boolean existId(long id) {
        AdminVo admin = new AdminVo();
        admin.setId(id);
        return adminDao.existUniqueKey(admin);
    }

    /**
     * 存在account
     */
    public boolean existAccount(String account) {
        AdminVo admin = new AdminVo();
        admin.setAccount(account);
        return adminDao.existUniqueKey(admin);
    }

    /**
     * 查找用户，通过id
     */
    public AdminVo findById(long id) {
        AdminVo admin = new AdminVo();
        admin.setId(id);
        return adminDao.findByUniqueKey(admin);
    }

    /**
     * 查找用户，通过account
     */
    public AdminVo findByAccount(String account) {
        AdminVo admin = new AdminVo();
        admin.setAccount(account);
        return adminDao.findByUniqueKey(admin);
    }

    /**
     * 新增用户(需id,account,pwd,createId)
     */
    @Transactional
    public Result insert(HttpServletRequest request, AdminVo admin) {
        // 插入失败
        if (!tryif(() -> adminDao.insert(admin))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> adminBakDao.insert(new AdminBak(admin.getId())));
        return Result.o();
    }

    /**
     * 登录(需account,pwd)
     */
    public Result login(HttpServletRequest request, AdminVo admin) {
        // 查找用户
        Admin u = findByAccount(admin.getAccount());
        // 用户不存在或账号禁用或密码错误
        if (u == null || u.getIsDelete() == 1 || !EncoderUtils.bCrypt(admin.getPwd(), u.getPwd())) {
            // 日志(登录失败)
            if (u == null) {
                recordLog(() -> adminLoginLogDao.insert(//
                        new AdminLoginLog(request, null, false)));
            } else {
                recordLog(() -> adminLoginLogDao.insert(//
                        new AdminLoginLog(request, u.getId(), false)));
            }
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        // 日志(登录成功)
        recordLog(() -> adminLoginLogDao.insert(//
                new AdminLoginLog(request, u.getId(), true)));
        // redis放入userId
        AuthUtils.setUserId(request, u.getId());
        return Result.o();
    }

    /**
     * 修改信息(需id,updateId;改不了id,pwd,isDelete,createId,createTime,updateTime,version)
     */
    @Transactional
    public Result changeInfo(AdminVo admin) {
        // 不能修改密码
        admin.setPwd(null);
        // 不能删除用户
        admin.setIsDelete(null);
        // 更新失败
        if (!tryif(() -> adminDao.updateById(admin))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> adminBakDao.insert(new AdminBak(admin.getId())));
        return Result.o(admin);
    }

    /**
     * 修改密码(需id,updateId,pwd,newPwd)
     */
    @Transactional
    public Result changePwd(AdminVo admin) {
        // 查找用户
        Admin u = findById(admin.getId());
        // 旧密码错误
        if (!EncoderUtils.bCrypt(admin.getPwd(), u.getPwd())) {
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        AdminVo u2 = new AdminVo();
        u2.setId(admin.getId());
        u2.setPwd(EncoderUtils.bCrypt(admin.getNewPwd()));
        u2.setUpdateId(admin.getUpdateId());
        // 更新失败
        if (!tryif(() -> adminDao.updateById(u2))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> adminBakDao.insert(new AdminBak(u2.getId())));
        return Result.o();
    }

}
