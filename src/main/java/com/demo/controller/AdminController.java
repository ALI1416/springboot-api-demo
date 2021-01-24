package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.AdminVo;
import com.demo.entity.vo.UserVo;
import com.demo.service.AdminService;
import com.demo.tool.Id;
import com.demo.util.AuthUtils;
import com.demo.util.EncoderUtils;
import com.demo.util.RedisUtils;

import lombok.AllArgsConstructor;

/**
 * <h1>管理员api</h1>
 *
 * <p>
 * createDate 2021/01/23 19:10:15
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController extends BaseController {

    private final HttpServletRequest request;
    private final AdminService adminService;

    /**
     * 存在account
     */
    @PostMapping("/existAccount")
    public Result existAccount(String account) {
        return Result.o(adminService.existAccount(account));
    }

    /**
     * 新增用户(需account,pwd)
     */
    @Auth
    @PostMapping("/insert")
    public Result insert(@RequestBody AdminVo admin) {
        if ((existEmpty(admin.getAccount(), admin.getPwd())) || admin.getPwd().length() != 32) {
            return Result.e1();
        }
        // 用户已存在
        if (adminService.existAccount(admin.getAccount())) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        admin.setId(Id.next());
        admin.setPwd(EncoderUtils.bCrypt(admin.getPwd()));
        admin.setCreateId(AuthUtils.getUserId(request));
        return adminService.insert(request, admin);
    }

    /**
     * 登录(需account,pwd)<br>
     * 默认账号：admin<br>
     * 默认密码：admin<br>
     * 默认密码MD5后：21232f297a57a5a743894a0e4a801fc3<br>
     * 默认密码hash加盐后：$2a$10$Jna0eHkLJ6X8XFDoOZq4QufchuF442kUzHVFTPS3gWvwWNTdSPrUu
     */
    @Auth(skipLogin = true)
    @PostMapping("/login")
    public Result login(@RequestBody AdminVo admin) {
        if ((existEmpty(admin.getAccount(), admin.getPwd())) || admin.getPwd().length() != 32) {
            return Result.e1();
        }
        return adminService.login(request, admin);
    }

    /**
     * 修改账号(需account)
     */
    @Auth
    @PostMapping("/changeAccount")
    public Result changeAccount(@RequestBody UserVo user) {
        // 用户已存在
        if (adminService.existAccount(user.getAccount())) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        Long id = AuthUtils.getUserId(request);
        AdminVo u = new AdminVo();
        u.setId(id);
        u.setUpdateId(id);
        u.setAccount(user.getAccount());
        return adminService.changeInfo(u);
    }

    /**
     * 修改个人信息(只能修改name,comment)
     */
    @Auth
    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody AdminVo admin) {
        Long id = AuthUtils.getUserId(request);
        AdminVo u = new AdminVo();
        u.setId(id);
        u.setUpdateId(id);
        u.setName(admin.getName());
        u.setComment(admin.getComment());
        return adminService.changeInfo(u);
    }

    /**
     * 修改密码(需pwd,newPwd)
     */
    @Auth
    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody AdminVo admin) {
        if (existNull(admin.getPwd(), admin.getNewPwd()) || admin.getPwd().length() != 32
                || admin.getNewPwd().length() != 32) {
            return Result.e1();
        }
        Long id = AuthUtils.getUserId(request);
        admin.setId(id);
        admin.setUpdateId(id);
        return adminService.changePwd(admin);
    }

    /**
     * 退出账号
     */
    @Auth
    @PostMapping("/logout")
    public Result logout() {
        String sign = AuthUtils.getSign(request);
        RedisUtils.delete(sign);
        return Result.o();
    }

    /**
     * 查看个人信息
     */
    @Auth
    @PostMapping("/showInfo")
    public Result showInfo() {
        Long id = AuthUtils.getUserId(request);
        AdminVo admin = adminService.findById(id);
        admin.setPwd(null);
        return Result.o(admin);
    }

}
