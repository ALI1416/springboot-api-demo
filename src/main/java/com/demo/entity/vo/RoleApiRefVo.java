package com.demo.entity.vo;

import com.demo.entity.po.RoleApiRef;

/**
 * <h1>api角色引用对象</h1>
 *
 * <p>
 * createDate 2021/01/24 16:21:42
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class RoleApiRefVo extends RoleApiRef {

    /**
     * 构造方法
     */
    public RoleApiRefVo() {

    }

    /**
     * 测试用构造方法
     */
    public RoleApiRefVo(Long id, Long roleId, Long treeId) {
        setId(id);
        setRoleId(roleId);
        setTreeId(treeId);
    }
}
