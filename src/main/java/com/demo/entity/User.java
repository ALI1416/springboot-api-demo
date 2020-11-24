package com.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>User实体类</h1>
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
@ToString
public class User {
    private Integer id;
    private String account;
    private String pwd;
    private String name;
    private Integer gender;
    private Integer year;
    private Integer isDelete;
}
