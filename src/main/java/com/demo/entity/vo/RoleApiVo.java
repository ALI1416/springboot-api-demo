package com.demo.entity.vo;

import com.demo.entity.po.RoleApi;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <h1>api角色值对象</h1>
 *
 * <p>
 * createDate 2021/01/21 21:48:05
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
public class RoleApiVo extends RoleApi {

    /**
     * 解析后的路径
     */
    public List<RoleApiVo> path;

    /**
     * 构造方法
     */
    public RoleApiVo() {

    }

    /**
     * 测试用构造方法
     */
    public RoleApiVo(Long id, String name) {
        setId(id);
        setName(name);
    }

}
