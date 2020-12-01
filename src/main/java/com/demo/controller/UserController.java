package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;
import com.demo.result.BatchResult;
import com.demo.result.Result;
import com.demo.result.ResultCode;
import com.demo.service.UserService;
import com.demo.util.EncoderUtils;
import com.demo.vo.UserVo;

import lombok.AllArgsConstructor;

/**
 * <h1>User控制层</h1>
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

    private UserService userService;

    /**
     * 是否存在该账号
     */
    @PostMapping("/existAccount")
    public Result existAccount(String account) {
        if (account == null || account.length() == 0) {
            return Result.e1();
        }
        return userService.existAccount(account);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (user.getAccount() == null || user.getPwd() == null || user.getAccount().length() == 0
                || user.getPwd().length() != 32) {
            return Result.e1();
        }
        user.setPwd(EncoderUtils.bCrypt(user.getPwd()));
        if (user.getName() == null || user.getName().length() == 0) {
            user.setName(user.getAccount());
        }
        if (user.getGender() == null) {
            user.setGender(0);
        }
        if (user.getYear() == null) {
            user.setYear(2000);
        }
        if (user.getIsDelete() == null) {
            user.setIsDelete(0);
        }
        return userService.register(user);
    }

    /**
     * 查找用户，通过id
     */
    @PostMapping("/findById")
    public Result findById(int id) {
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
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (user.getAccount() == null || user.getPwd() == null || user.getAccount().length() == 0
                || user.getPwd().length() != 32) {
            return Result.e1();
        }
        return userService.login(user);
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody User user) {
        if (user.getId() == null) {
            return Result.e1();
        }
        return userService.changeInfo(user);
    }

    /**
     * 修改密码
     */
    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody UserVo user) {
        if (user.getId() == null || user.getPwd() == null || user.getNewPwd() == null || user.getPwd().length() != 32
                || user.getNewPwd().length() != 32) {
            return Result.e1();
        }
        return userService.changePwd(user);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        return userService.deleteById(id);
    }

    /**
     * 批量插入(明文密码)
     */
    @PostMapping("/batchRegister")
    public Result batchRegister(@RequestBody List<User> user) {
        // 输入数据完整性检查
        BatchResult<User> result = new BatchResult<>();
        for (User u : user) {
            if (u.getAccount() == null || u.getPwd() == null || u.getAccount().length() == 0
                    || u.getPwd().length() == 0) {
                result.add(false, u);
            } else {
                result.add(u);
            }
        }
        if (!result.ok()) {
            return Result.e(ResultCode.USER_BATCH_REGISTER_ERROR, result);
        }
        // 补充缺失信息
        for (User u : user) {
            u.setPwd(EncoderUtils.bCrypt(EncoderUtils.md5(u.getPwd())));
            if (u.getName() == null || u.getName().length() == 0) {
                u.setName(u.getAccount());
            }
            if (u.getGender() == null) {
                u.setGender(0);
            }
            if (u.getYear() == null) {
                u.setYear(2000);
            }
            if (u.getIsDelete() == null) {
                u.setIsDelete(0);
            }
        }
        return userService.batchRegister(user);
    }

    /**
     * 查找全部
     * 
     * @param pages   页码
     * @param rows    每页条数
     * @param orderBy 排序
     */
    @PostMapping("/findAll")
    public Result findAll(@RequestParam(defaultValue = "1") int pages, @RequestParam(defaultValue = "20") int rows,
            @RequestParam(defaultValue = "id desc") String orderBy) {
        return userService.findAll(pages, rows, orderBy);
    }

}
