package com.demo.entity.pojo;

import com.demo.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>Redis存储数据实体</h1>
 *
 * <p>
 * createDate 2020/12/06 16:09:53
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class Redis extends BaseEntity {
    private Integer id;
    private String token;
}
