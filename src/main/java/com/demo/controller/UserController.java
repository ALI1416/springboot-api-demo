package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.UserVo;
import com.demo.service.UserService;
import com.demo.tool.Id;
import com.demo.util.AuthUtils;
import com.demo.util.EncoderUtils;
import com.demo.util.RegexUtils;
import com.demo.util.StringUtils;

import lombok.AllArgsConstructor;

/**
 * <h1>User api</h1>
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
public class UserController {

    private final HttpServletRequest request;
    private final UserService userService;

    /**
     * 是否存在该id
     */
    @PostMapping("/existId")
    public Result existId(long id) {
        return Result.o(userService.existId(id));
    }

    /**
     * 查找用户，通过id
     */
    @PostMapping("/findById")
    public Result findById(long id) {
        return userService.findById(id);
    }

    /**
     * 查找用户，通过account
     */
    @PostMapping("/findByAccount")
    public Result findByAccount(String account) {
        return userService.findByAccount(account);
    }

    /**
     * 注册，通过account
     */
    @Auth(skipLogin = true)
    @PostMapping("/register")
    public Result register(@RequestBody UserVo user) {
        if (StringUtils.existEmpty(user.getAccount(), user.getPwd(), user.getCaptcha()) //
                || !RegexUtils.isAccount(user.getAccount()) //
                || user.getPwd().length() != 32) {
            return Result.e1();
        }
        // 用户已存在
        if (userService.existAccount(user.getAccount()).isOk()) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        // 验证码错误
        if (!AuthUtils.correctCaptcha(request, user.getCaptcha())) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR);
        }
        user.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        if (StringUtils.existEmpty(user.getName())) {
            user.setName(user.getAccount());
        }
        user.setId(Id.next());
        return userService.register(request, user);
    }

    /**
     * 注册，通过email
     */
    @Auth(skipLogin = true)
    @PostMapping("/registerByEmail")
    public Result registerByEmail(@RequestBody UserVo user) {
        if (StringUtils.existEmpty(user.getEmail(), user.getPwd(), user.getCaptcha(), user.getEmailCaptcha()) //
                || !RegexUtils.isEmail(user.getEmail())//
                || user.getPwd().length() != 32) {
            return Result.e1();
        }
        // 邮箱已存在
        if (userService.existEmail(user.getEmail()).isOk()) {
            return Result.e(ResultCodeEnum.EMAIL_HAS_EXISTED);
        }
        // 验证码错误
        if (!AuthUtils.correctCaptcha(request, user.getCaptcha())) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR);
        }
        // 邮件验证码错误
        if (!AuthUtils.correctEmailCaptcha(request, user.getEmail(), user.getEmailCaptcha())) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR, "邮件验证码错误");
        }
        user.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        if (StringUtils.existEmpty(user.getName())) {
            user.setName(user.getAccount());
        }
        user.setId(Id.next());
        return userService.registerByEmail(request, user);
    }

    /**
     * 绑定email
     */
    @Auth(skipLogin = true)
    @PostMapping("/bindEmail")
    public Result bindEmail(@RequestBody UserVo user) {
        if (StringUtils.existEmpty(user.getEmail(), user.getEmailCaptcha()) //
                || !RegexUtils.isEmail(user.getEmail())) {
            return Result.e1();
        }
        // 邮箱已存在
        if (userService.existEmail(user.getEmail()).isOk()) {
            return Result.e(ResultCodeEnum.EMAIL_HAS_EXISTED);
        }
        // 邮件验证码错误
        if (!AuthUtils.correctEmailCaptcha(request, user.getEmail(), user.getEmailCaptcha())) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR, "邮件验证码错误");
        }
        User u = new User();
        u.setId(AuthUtils.getUserId(request));
        u.setEmail(user.getEmail());
        u.setUseEmailLogin(1);
        return userService.changeInfo(u);
    }

    /**
     * 解绑email
     */
    @Auth(skipLogin = true)
    @PostMapping("/unbindEmail")
    public Result unbindEmail(@RequestBody UserVo user) {
        if (StringUtils.existEmpty(user.getEmail()) || !RegexUtils.isEmail(user.getEmail())) {
            return Result.e1();
        }
        User u1 = (User) userService.findByEmail(user.getEmail()).getData();
        // 邮箱不匹配
        if (!user.getEmail().equals(u1.getEmail())) {
            return Result.e(ResultCodeEnum.EMAIL_ERROR);
        }
        User u = new User();
        Long id = AuthUtils.getUserId(request);
        u.setId(id);
        u.setEmail(String.valueOf(id));
        u.setUseEmailLogin(0);
        return userService.changeInfo(u);
    }

    /**
     * 登录，account和email都可以，需要放入account中
     */
    @Auth(skipLogin = true)
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if ((StringUtils.existEmpty(user.getAccount(), user.getPwd())) || user.getPwd().length() != 32) {
            return Result.e1();
        }
        return userService.login(request, user);
    }

    /**
     * 修改用户信息
     */
    @Auth
    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody User user) {
        user.setId(AuthUtils.getUserId(request));
        return userService.changeInfo(user);
    }

    /**
     * 修改密码
     */
    @Auth
    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody UserVo user) {
        if (user.getPwd() == null || user.getNewPwd() == null || user.getPwd().length() != 32
                || user.getNewPwd().length() != 32) {
            return Result.e1();
        }
        user.setId(AuthUtils.getUserId(request));
        return userService.changePwd(user);
    }

    /**
     * 删除
     */
    @Auth
    @PostMapping("/deleteById")
    public Result deleteById() {
        return userService.deleteById(AuthUtils.getUserId(request));
    }

    /**
     * 批量插入(明文密码)
     */
    @PostMapping("/batchRegister")
    public Result batchRegister(@RequestBody List<User> user) {
        // 数据完整性检查
        ResultBatch<User> result = new ResultBatch<>();
        for (User u : user) {
            if (StringUtils.existEmpty(u.getAccount())) {
                result.add(false, u, "账号不能为空");
            } else {
                result.add(u);
            }
        }
        // 数据不完整
        if (!result.isOk()) {
            return Result.e(ResultCodeEnum.BATCH_OPERATION_ERROR, result);
        }
        // 补充缺失信息
        for (User u : user) {
            if (StringUtils.existEmpty(u.getPwd())) {
                u.setPwd(u.getAccount());
            }
            u.setPwd(EncoderUtils.bCrypt(EncoderUtils.md5(u.getPwd())));
            if (StringUtils.existEmpty(u.getName())) {
                u.setName(u.getAccount());
            }
            u.setId(Id.next());
        }
        return userService.batchRegister(user);
    }

    /**
     * 精确查询
     */
    @PostMapping("/findExact")
    public Result findExact(@RequestBody @Nullable User user) {
        return userService.findExact(user);
    }

    /**
     * 模糊查询
     */
    @PostMapping("/find")
    public Result find(@RequestBody @Nullable UserVo user) {
        return userService.find(user);
    }

}
