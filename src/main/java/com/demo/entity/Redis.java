package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>Redis存储数据实体类</h1>
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
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Redis {
    private Integer id;
    private String token;
}
