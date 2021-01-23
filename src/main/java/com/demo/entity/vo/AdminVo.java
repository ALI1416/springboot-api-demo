package com.demo.entity.vo;

import com.demo.entity.po.Admin;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>管理员值对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:48:05
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class AdminVo extends Admin {

    /**
     * 新密码
     */
    private String newPwd;

}
