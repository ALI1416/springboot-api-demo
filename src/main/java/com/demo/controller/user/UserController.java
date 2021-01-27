package com.demo.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.ResultCodeEnum;
import com.demo.controller.BaseController;
import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.UserVo;
import com.demo.service.UserService;
import com.demo.tool.Id;
import com.demo.util.AuthUtils;
import com.demo.util.EncoderUtils;
import com.demo.util.RedisUtils;
import com.demo.util.RegexUtils;

import lombok.AllArgsConstructor;

/**
 * <h1>用户api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController extends BaseController {

    private final HttpServletRequest request;
    private final UserService userService;

    /**
     * 存在account
     */
    @PostMapping("/existAccount")
    public Result existAccount(String account) {
        return Result.o(userService.existAccount(account));
    }

    /**
     * 存在email
     */
    @PostMapping("/existEmail")
    public Result existEmail(String email) {
        return Result.o(userService.existEmail(email));
    }

    /**
     * 注册，通过account(需要account,pwd,captcha)
     */
    @Auth(skipLogin = true)
    @PostMapping("/register")
    public Result register(@RequestBody UserVo user) {
        if (existEmpty(user.getAccount(), user.getPwd(), user.getCaptcha()) //
                || !RegexUtils.isAccount(user.getAccount()) //
                || user.getPwd().length() != 32) {
            return Result.e1();
        }
        // 验证码错误
        if (!AuthUtils.correctCaptcha(request, user.getCaptcha())) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR);
        }
        // 用户已存在
        if (userService.existAccount(user.getAccount())) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        user.setId(Id.next());
        user.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        return userService.register(request, user);
    }

    /**
     * 注册，通过email(需要email,captcha,emailCaptcha)
     */
    @Auth(skipLogin = true)
    @PostMapping("/registerByEmail")
    public Result registerByEmail(@RequestBody UserVo user) {
        if (existEmpty(user.getEmail(), user.getPwd(), user.getCaptcha(), user.getEmailCaptcha()) //
                || !RegexUtils.isEmail(user.getEmail())//
                || user.getPwd().length() != 32) {
            return Result.e1();
        }
        // 验证码错误
        if (!AuthUtils.correctCaptcha(request, user.getCaptcha())) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR);
        }
        // 邮箱验证码错误
        if (!AuthUtils.correctEmailCaptcha(request, user.getEmail(), user.getEmailCaptcha())) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR, "邮箱验证码错误");
        }
        // 邮箱已存在
        if (userService.existEmail(user.getEmail())) {
            return Result.e(ResultCodeEnum.EMAIL_HAS_EXISTED);
        }
        user.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        if (isEmpty(user.getName())) {
            user.setName(user.getAccount());
        }
        user.setId(Id.next());
        return userService.registerByEmail(request, user);
    }

    /**
     * 登录(需要account或email，都用account传输)
     */
    @Auth(skipLogin = true)
    @PostMapping("/login")
    public Result login(@RequestBody UserVo user) {
        if ((existEmpty(user.getAccount(), user.getPwd())) || user.getPwd().length() != 32) {
            return Result.e1();
        }
        return userService.login(request, user);
    }

    /**
     * 绑定email(需要email,emailCaptcha)
     */
    @Auth
    @PostMapping("/bindEmail")
    public Result bindEmail(@RequestBody UserVo user) {
        if (existEmpty(user.getEmail(), user.getEmailCaptcha()) //
                || !RegexUtils.isEmail(user.getEmail())) {
            return Result.e1();
        }
        Long id = AuthUtils.getUserId(request);
        User u1 = userService.findById(id);
        // 已绑定邮箱，需要先解绑
        if (!u1.getEmail().equals(id.toString())) {
            return Result.e();
        }
        // 邮箱验证码错误
        if (!AuthUtils.correctEmailCaptcha(request, user.getEmail(), user.getEmailCaptcha())) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR, "邮箱验证码错误");
        }
        // 邮箱已存在
        if (userService.existEmail(user.getEmail())) {
            return Result.e(ResultCodeEnum.EMAIL_HAS_EXISTED);
        }
        UserVo u = new UserVo();
        u.setId(id);
        u.setEmail(user.getEmail());
        return userService.changeInfo(u);
    }

    /**
     * 解绑email
     */
    @Auth
    @PostMapping("/unbindEmail")
    public Result unbindEmail() {
        Long id = AuthUtils.getUserId(request);
        UserVo user = userService.findById(id);
        // 没有设置密码，不能解绑
        if ("".equals(user.getPwd())) {
            Result.e();
        }
        // 没有设置账号或qq，不能解绑
        if (user.getAccount().equals(user.getId().toString()) || user.getQqOpenid().equals(user.getId().toString())) {
            Result.e();
        }
        UserVo u = new UserVo();
        u.setId(id);
        u.setEmail(String.valueOf(id));
        return userService.changeInfo(u);
    }

    /**
     * 解绑qq
     */
    @Auth
    @PostMapping("/unbindQq")
    public Result unbindQq() {
        Long id = AuthUtils.getUserId(request);
        UserVo user = userService.findById(id);
        // 没有设置密码，不能解绑
        if ("".equals(user.getPwd())) {
            Result.e();
        }
        // 没有设置账号或邮箱，不能解绑
        if (user.getAccount().equals(user.getId().toString()) || user.getEmail().equals(user.getId().toString())) {
            Result.e();
        }
        UserVo u = new UserVo();
        u.setId(id);
        u.setQqOpenid(String.valueOf(id));
        return userService.changeInfo(u);
    }

    /**
     * 修改账号(需account)
     */
    @Auth
    @PostMapping("/changeAccount")
    public Result changeAccount(@RequestBody UserVo user) {
        // 用户已存在
        if (userService.existAccount(user.getAccount())) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        Long id = AuthUtils.getUserId(request);
        UserVo u = new UserVo();
        u.setId(id);
        u.setAccount(user.getAccount());
        return userService.changeInfo(u);
    }

    /**
     * 修改个人信息(只能修改name,gender,year,profile,comment)
     */
    @Auth
    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody UserVo user) {
        Long id = AuthUtils.getUserId(request);
        UserVo u = new UserVo();
        u.setId(id);
        u.setName(user.getName());
        u.setGender(user.getGender());
        u.setYear(user.getYear());
        u.setProfile(user.getProfile());
        u.setComment(user.getComment());
        return userService.changeInfo(u);
    }

    /**
     * 修改密码(需pwd,newPwd)
     */
    @Auth
    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody UserVo user) {
        if (existNull(user.getPwd(), user.getNewPwd()) || user.getPwd().length() != 32
                || user.getNewPwd().length() != 32) {
            return Result.e1();
        }
        Long id = AuthUtils.getUserId(request);
        user.setId(id);
        return userService.changePwd(user);
    }

    /**
     * 当使用第三方注册时，由于没有设置密码，需要手动设置密码(需pwd)
     */
    @Auth
    @PostMapping("/setPwd")
    public Result setPwd(@RequestBody UserVo user) {
        if (user.getPwd() == null || user.getPwd().length() != 32) {
            return Result.e1();
        }
        Long id = AuthUtils.getUserId(request);
        user.setId(id);
        return userService.setPwd(user);
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
     * 注销账号
     */
    @Auth
    @PostMapping("/delete")
    public Result delete() {
        Long id = AuthUtils.getUserId(request);
        Result result = userService.deleteById(id);
        // 注销失败
        if (!result.isOk()) {
            return result;
        }
        // 注销成功，退出账号
        return logout();
    }

    /**
     * 查看个人信息
     */
    @Auth
    @PostMapping("/showInfo")
    public Result showInfo() {
        Long id = AuthUtils.getUserId(request);
        UserVo user = userService.findById(id);
        if (isEmpty(user.getPwd())) {
            user.setPwd("empty password");
        } else {
            user.setPwd(null);
        }
        return Result.o(user);
    }

}
