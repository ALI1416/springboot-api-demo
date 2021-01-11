package com.demo.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>基实体</h1>
 *
 * <p>
 * createDate 2020/11/22 15:43:28
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class BaseEntity {

    /* ====================po==================== */
    /**
     * id
     */
    private Long id;
    /**
     * 已删除
     */
    private Integer isDelete;
    /**
     * 创建者id
     */
    private Integer createId;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新者id
     */
    private Integer updateId;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 版本
     */
    private Integer version;

    /* ====================vo==================== */
    /**
     * 分页-页码
     */
    private Integer pages;
    /**
     * 分页-每页条数
     */
    private Integer rows;
    /**
     * 分页-排序
     */
    private String orderBy;

    /* ====================all==================== */
    /**
     * 重写toString成JSON格式
     */
    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(//
                this, // 对象本身
                "yyyy-MM-dd HH:mm:ss", // 日期格式化样式
                SerializerFeature.DisableCircularReferenceDetect, // 禁用对象循环引用：避免$ref
                SerializerFeature.WriteNonStringValueAsString// 非String转为String：防止long丢失精度
        );
    }

}
