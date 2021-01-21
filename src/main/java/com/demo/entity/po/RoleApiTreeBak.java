package com.demo.entity.po;

import com.demo.tool.Id;

/**
 * <h1>api角色树备份表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:07:48
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class RoleApiTreeBak extends RoleApiTree {

    /**
     * 构造函数(自动生成id)
     */
    public RoleApiTreeBak() {
        setId(Id.next());
    }

    /**
     * 构造函数(自动生成id)
     *
     * @param refId refId
     */
    public RoleApiTreeBak(Long refId) {
        setId(Id.next());
        setRefId(refId);
    }

}
