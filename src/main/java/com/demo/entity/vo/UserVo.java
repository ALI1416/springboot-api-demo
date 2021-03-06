package com.demo.entity.vo;

import com.demo.entity.po.User;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户值对象</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class UserVo extends User {

    /**
     * 新密码
     */
    private String newPwd;
    /**
     * 出生年-否定
     */
    private Integer yearNot;
    /**
     * 出生年-结束
     */
    private Integer yearEnd;
    /**
     * 验证码
     */
    private String captcha;
    /**
     * 邮箱验证码
     */
    private String emailCaptcha;
}
