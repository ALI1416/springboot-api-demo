package com.demo.entity.po;

import com.demo.tool.Id;

/**
 * <h1>用户备份表持久化对象</h1>
 *
 * <p>
 * createDate 2021/01/14 16:23:13
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class UserBak extends User {

    /**
     * 构造函数(自动生成id)
     */
    public UserBak() {
        setId(Id.next());
    }

    /**
     * 构造函数(自动生成id)
     * 
     * @param refId refId
     */
    public UserBak(Long refId) {
        setId(Id.next());
        setRefId(refId);
    }
}
