package com.demo.entity.po;

import com.demo.tool.Id;

/**
 * <h1>api角色备份表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:06:53
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class RoleApiBak extends RoleApi {

    /**
     * 构造函数(自动生成id)
     *
     * @param refId refId
     */
    public RoleApiBak(Long refId) {
        setId(Id.next());
        setRefId(refId);
    }

}
