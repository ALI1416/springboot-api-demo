package com.demo.service;

import com.demo.base.ServiceBase;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.po.Admin;
import com.demo.entity.po.AdminBak;
import com.demo.entity.po.AdminLoginLog;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.AdminVo;
import com.demo.mapper.AdminBakMapper;
import com.demo.mapper.AdminLoginLogMapper;
import com.demo.mapper.AdminMapper;
import com.demo.util.AuthUtils;
import com.demo.util.EncoderUtils;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AdminService extends ServiceBase {

    private final AdminMapper adminMapper;
    private final AdminBakMapper adminBakMapper;
    private final AdminLoginLogMapper adminLoginLogMapper;

    /* ==================== 通用方法 ==================== */
    //region

    /**
     * 存在id
     */
    public boolean existId(long id) {
        AdminVo admin = new AdminVo();
        admin.setId(id);
        return adminMapper.existUniqueKey(admin);
    }

    /**
     * 存在account
     */
    public boolean existAccount(String account) {
        AdminVo admin = new AdminVo();
        admin.setAccount(account);
        return adminMapper.existUniqueKey(admin);
    }

    /**
     * 查找用户，通过id
     */
    public AdminVo findById(long id) {
        AdminVo admin = new AdminVo();
        admin.setId(id);
        return adminMapper.findByUniqueKey(admin);
    }

    /**
     * 查找用户，通过account
     */
    public AdminVo findByAccount(String account) {
        AdminVo admin = new AdminVo();
        admin.setAccount(account);
        return adminMapper.findByUniqueKey(admin);
    }
    //endregion

    /**
     * 新增用户(需id,account,pwd,createId)
     */
    @Transactional
    public Result insert(HttpServletRequest request, AdminVo admin) {
        // 插入失败
        if (!tryif(() -> adminMapper.insert(admin))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> adminBakMapper.insert(new AdminBak(admin.getId())));
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
                recordLog(() -> adminLoginLogMapper.insert(//
                        new AdminLoginLog(request, null, false)));
            } else {
                recordLog(() -> adminLoginLogMapper.insert(//
                        new AdminLoginLog(request, u.getId(), false)));
            }
            return Result.e(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        // 日志(登录成功)
        recordLog(() -> adminLoginLogMapper.insert(//
                new AdminLoginLog(request, u.getId(), true)));
        // redis放入userId
        AuthUtils.setUserId(request, u.getId());
        return Result.o();
    }

    /**
     * 修改信息(需id;至少一个account,pwd,name,comment)
     */
    @Transactional
    public Result changeInfo(AdminVo admin) {
        // 不能修改密码
        admin.setPwd(null);
        // 更新失败
        if (!tryif(() -> adminMapper.update(admin))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> adminBakMapper.insert(new AdminBak(admin.getId())));
        return Result.o(admin);
    }

    /**
     * 修改密码(需id,pwd,newPwd)
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
        // 更新失败
        if (!tryif(() -> adminMapper.update(u2))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> adminBakMapper.insert(new AdminBak(u2.getId())));
        return Result.o();
    }

    /**
     * 删除(需id)
     */
    @Transactional
    public Result deleteById(Long id) {
        // 删除失败
        if (!tryif(() -> adminMapper.deleteById(id))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> adminBakMapper.insert(new AdminBak(id)));
        return Result.o();
    }

}
