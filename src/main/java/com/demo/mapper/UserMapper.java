package com.demo.mapper;

import java.util.List;

import com.demo.entity.excel.UserExport;
import com.demo.entity.vo.UserVo;

/**
 * <h1>用户Mapper</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface UserMapper {

    /**
     * 插入(需id,account,pwd,createId)
     */
    int insert(UserVo user);

    /**
     * 注册，通过account(需id,account,pwd)
     */
    int register(UserVo user);

    /**
     * 注册，通过email(需id,email,pwd)
     */
    int registerByEmail(UserVo user);

    /**
     * 注册，通过qq(需id,name,gender,year,qqName,qqOpenid)
     */
    int registerByQq(UserVo user);

    /**
     * 存在一个唯一键(仅一个id,account,email,qqOpenid)
     */
    boolean existUniqueKey(UserVo user);

    /**
     * 查询一个唯一键(仅一个id,account,email,qqOpenid)
     */
    UserVo findByUniqueKey(UserVo user);

    /**
     * 更新(需id;至少一个account,pwd,name,gender,year,profile,comment,email,qqOpenid,qqName)
     */
    int update(UserVo user);

    /**
     * 删除(需id)
     */
    int deleteById(long id);

    /**
     * 查找account列表(需account)
     */
    List<UserVo> findByAccountList(List<String> accountList);

    /**
     * 精确查询
     */
    List<UserVo> findExact(UserVo user);

    /**
     * 查询
     */
    List<UserVo> find(UserVo user);
    
    /**
     * es查询
     */
    List<UserVo> esFind(UserVo user);

    /**
     * 导出
     */
    List<UserExport> export(UserVo user);

}
