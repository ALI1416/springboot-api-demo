package com.demo.vo;

import com.demo.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>User值对象</h1>
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
@ToString(callSuper = true)
public class UserVo extends User {
    private String newPwd;
    private Integer yearNot;
    private Integer yearEnd;
}
