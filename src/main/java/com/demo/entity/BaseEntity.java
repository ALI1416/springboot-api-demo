package com.demo.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {
    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }

}
