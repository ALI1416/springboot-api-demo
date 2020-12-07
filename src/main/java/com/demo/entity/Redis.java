package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>Redis存储数据实体</h1>
 *
 * <p>
 * createDate 2020/12/06 16:09:53
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Redis extends Base {
    private Integer id;
    private String token;
}
