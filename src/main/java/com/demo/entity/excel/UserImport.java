package com.demo.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户导入</h1>
 *
 * <p>
 * createDate 2021/02/04 15:29:47
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class UserImport {

    /**
     * 账号
     */
    @ExcelProperty("账号")
    private String account;
    /**
     * 密码
     */
    @ExcelProperty("密码")
    private String pwd;

}
