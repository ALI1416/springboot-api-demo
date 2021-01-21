package com.demo.dao;

import com.demo.entity.vo.UserVo;

import java.util.List;

/**
 * <h1>用户业务逻辑接口</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface UserDao {

    /**
     * 注册，通过account(需id,account,pwd)
     */
    int register(UserVo user);

    /**
     * 注册，通过email(需id,email,pwd)
     */
    int registerByEmail(UserVo user);

    /**
     * 注册，通过qq(需id,qqOpenid)
     */
    int registerByQq(UserVo user);

    /**
     * 存在一个唯一键(需其一id,account,email,qqOpenid)
     */
    boolean existUniqueKey(UserVo user);

    /**
     * 查询一个唯一键(需其一id,account,email,qqOpenid)
     */
    UserVo findByUniqueKey(UserVo user);

    /**
     * 更新(需id,updateId;改不了id,createId,createTime,updateTime,version)
     */
    int updateById(UserVo user);

    /**
     * 精确查询
     */
    List<UserVo> findExact(UserVo user);

    /**
     * 查询
     */
    List<UserVo> find(UserVo user);

}
