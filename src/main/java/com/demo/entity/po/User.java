package com.demo.entity.po;

import com.demo.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>User持久对象</h1>
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
public class User extends BaseEntity {
    /**
     * id
     */
    private Integer id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 出生年
     */
    private Integer year;
    /**
     * 是否删除
     */
    private Integer isDelete;

}
