package com.demo.entity.excel.export;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DemoData {
    @ColumnWidth(15)
    @ExcelProperty("字符串标题")
    private String string;
    @ColumnWidth(20)
    @ExcelProperty("日期标题")
    private Date date;
    @ColumnWidth(15)
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}