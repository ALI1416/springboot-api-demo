package com.demo.entity.excel;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户导出</h1>
 *
 * <p>
 * createDate 2021/02/04 15:30:52
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class UserExport {

    /**
     * 账号
     */
    @ExcelProperty("账号")
    private String account;
    /**
     * 密码
     */
    @ExcelProperty("密码")
    private String hasPwd;
    /**
     * 用户名
     */
    @ExcelProperty("用户名")
    private String name;
    /**
     * 性别
     */
    @ExcelProperty("性别")
    private String gender;
    /**
     * 出生年
     */
    @ExcelProperty("出生年")
    private Integer year;
    /**
     * 个人简介
     */
    @ExcelProperty("个人简介")
    private String profile;
    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String comment;
    /**
     * 电子邮箱
     */
    @ExcelProperty("电子邮箱")
    private String email;
    /**
     * 已绑定QQ
     */
    @ExcelProperty("已绑定QQ")
    private String bindQq;
    /**
     * qq昵称
     */
    @ExcelProperty("qq昵称")
    private String qqName;
    /**
     * 已删除
     */
    @ExcelProperty("已删除")
    private String isDelete;
    /**
     * 创建者
     */
    @ExcelProperty("创建者")
    private String createName;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private Date createTime;
    /**
     * 更改者
     */
    @ExcelProperty("更改者")
    private String updateName;
    /**
     * 更改时间
     */
    @ExcelProperty("更改时间")
    private Date updateTime;
    /**
     * 版本
     */
    @ExcelProperty("版本")
    private Integer version;

}
