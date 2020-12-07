package com.demo.po;

import com.demo.entity.Base;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>User持久层</h1>
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
public class User extends Base {
    private Integer id;
    private String account;
    private String pwd;
    private String name;
    private Integer gender;
    private Integer year;
    private Integer isDelete;

}
