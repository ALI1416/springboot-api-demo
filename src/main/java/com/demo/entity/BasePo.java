package com.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * <h1>基持久对象</h1>
 *
 * <p>
 * createDate 2020/12/07 19:51:52
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class BasePo extends BaseEntity {
    private Map<String, Object> map;
}
