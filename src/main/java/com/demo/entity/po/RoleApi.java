package com.demo.entity.po;

import com.demo.base.EntityBase;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>api角色表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:06:42
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class RoleApi extends EntityBase {

    /**
     * 角色名
     */
    private String name;

}
