package com.demo.controller;

import com.demo.annotation.Auth;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.AdminVo;
import com.demo.service.AdminService;
import com.demo.util.AuthUtils;
import com.demo.util.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
     * 登录(需account,pwd)
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
     * 修改个人信息(只能修改account,name,comment)
     */
    @Auth
    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody AdminVo admin) {
        Long id = AuthUtils.getUserId(request);
        AdminVo u = new AdminVo();
        u.setId(id);
        u.setUpdateId(id);
        u.setAccount(admin.getAccount());
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
        if (existNull(admin.getPwd(), admin.getNewPwd()) || admin.getPwd().length() != 32 || admin.getNewPwd().length() != 32) {
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
     * 查看用户信息
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
