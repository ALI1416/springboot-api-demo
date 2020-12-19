package com.demo.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>基实体</h1>
 *
 * <p>
 * createDate 2020/11/22 15:43:28
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {
    /**
     * 页码(从1开始)
     */
    private Integer pages;
    /**
     * 每页条数
     */
    private Integer rows;
    /**
     * 排序
     */
    private String orderBy;

    /**
     * 重写toString成JSON格式
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }

}
