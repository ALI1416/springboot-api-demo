package com.demo.entity.po;

import com.demo.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>api角色引用表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:07:04
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class RoleApiRef extends BaseEntity {

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色树id
     */
    private Long treeId;

}
