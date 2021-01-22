package com.demo.entity.po;

import com.demo.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>api角色树表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:07:34
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class RoleApiTree extends BaseEntity {

    /**
     * 父id
     */
    private Long parentId;
    /**
     * 路径
     */
    private String path;
    /**
     * 描述
     */
    private String describe;

}
