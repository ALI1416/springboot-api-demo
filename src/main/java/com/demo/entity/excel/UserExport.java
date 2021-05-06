package com.demo.entity.excel;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import com.demo.base.ToStringBase;
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
public class UserExport extends ToStringBase {

    /**
     * 账号
     */
    @ColumnWidth(10)
    @ExcelProperty("账号")
    private String account;
    /**
     * 密码
     */
    @ColumnWidth(10)
    @ExcelProperty("密码")
    private String hasPwd;
    /**
     * 用户名
     */
    @ColumnWidth(10)
    @ExcelProperty("用户名")
    private String name;
    /**
     * 性别
     */
    @ColumnWidth(10)
    @ExcelProperty("性别")
    private String gender;
    /**
     * 出生年
     */
    @ColumnWidth(10)
    @ExcelProperty("出生年")
    private Integer year;
    /**
     * 个人简介
     */
    @ColumnWidth(12)
    @ExcelProperty("个人简介")
    private String profile;
    /**
     * 备注
     */
    @ColumnWidth(10)
    @ExcelProperty("备注")
    private String comment;
    /**
     * 电子邮箱
     */
    @ColumnWidth(20)
    @ExcelProperty("电子邮箱")
    private String email;
    /**
     * 已绑定QQ
     */
    @ColumnWidth(12)
    @ExcelProperty("已绑定QQ")
    private String bindQq;
    /**
     * QQ昵称
     */
    @ColumnWidth(10)
    @ExcelProperty("QQ昵称")
    private String qqName;
    /**
     * 已删除
     */
    @ColumnWidth(10)
    @ExcelProperty("已删除")
    private String isDelete;
    /**
     * 创建者
     */
    @ColumnWidth(10)
    @ExcelProperty("创建者")
    private String createName;
    /**
     * 创建时间
     */
    @ColumnWidth(20)
    @ExcelProperty("创建时间")
    private Date createTime;
    /**
     * 更改者
     */
    @ColumnWidth(10)
    @ExcelProperty("更改者")
    private String updateName;
    /**
     * 更改时间
     */
    @ColumnWidth(20)
    @ExcelProperty("更改时间")
    private Date updateTime;
    /**
     * 版本
     */
    @ColumnWidth(10)
    @ExcelProperty("版本")
    private Integer version;

}
