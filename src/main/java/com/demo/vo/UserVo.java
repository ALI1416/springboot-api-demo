package com.demo.vo;

import com.demo.po.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo extends User {
    private String newPwd;
    private Integer yearNot;
    private Integer yearEnd;
}
