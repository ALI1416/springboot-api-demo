package com.demo.entity.vo;

import com.demo.entity.po.RoleApiTree;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <h1>api角色树值对象</h1>
 *
 * <p>
 * createDate 2021/01/22 14:17:25
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class RoleApiTreeVo extends RoleApiTree {

    /**
     * 子节点
     */
    private List<RoleApiTreeVo> childs;

    /**
     * 构造方法
     */
    public RoleApiTreeVo() {

    }

    /**
     * 测试用构造方法
     */
    public RoleApiTreeVo(Long id, Long parentId, String path) {
        setId(id);
        setParentId(parentId);
        setPath(path);
    }

}
