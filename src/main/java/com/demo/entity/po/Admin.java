package com.demo.entity.po;

import com.demo.base.EntityBase;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>管理员表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:05:51
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class Admin extends EntityBase {

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 用户名
     */
    private String name;
    /**
     * 备注
     */
    private String comment;
    /**
     * api角色id
     */
    private Integer roleApiId;
    /**
     * web角色id
     */
    private Integer roleWebId;

}
