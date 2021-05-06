package com.demo.service;

import com.demo.base.ServiceBase;
import com.demo.entity.po.RoleApiBak;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.RoleApiVo;
import com.demo.mapper.RoleApiBakMapper;
import com.demo.mapper.RoleApiMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>api角色服务</h1>
 *
 * <p>
 * createDate 2021/01/23 19:13:01
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Service
@AllArgsConstructor
public class RoleApiService extends ServiceBase {

    private final RoleApiMapper roleApiMapper;
    private final RoleApiBakMapper roleApiBakMapper;

    /**
     * 存在id
     */
    public boolean existId(long id) {
        RoleApiVo roleApi = new RoleApiVo();
        roleApi.setId(id);
        return roleApiMapper.existUniqueKey(roleApi);
    }

    /**
     * 存在name
     */
    public boolean existName(String name) {
        RoleApiVo roleApi = new RoleApiVo();
        roleApi.setName(name);
        return roleApiMapper.existUniqueKey(roleApi);
    }

    /**
     * 查找，通过id
     */
    public RoleApiVo findById(long id) {
        RoleApiVo roleApi = new RoleApiVo();
        roleApi.setId(id);
        return roleApiMapper.findByUniqueKey(roleApi);
    }

    /**
     * 查找，通过name
     */
    public RoleApiVo findByName(String name) {
        RoleApiVo roleApi = new RoleApiVo();
        roleApi.setName(name);
        return roleApiMapper.findByUniqueKey(roleApi);
    }

    /**
     * 新增(需id,name,createId)
     */
    @Transactional
    public Result insert(HttpServletRequest request, RoleApiVo roleApi) {
        // 插入失败
        if (!tryif(() -> roleApiMapper.insert(roleApi))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> roleApiBakMapper.insert(new RoleApiBak(roleApi.getId())));
        return Result.o();
    }

    /**
     * 修改信息(需id,updateId,name)
     */
    @Transactional
    public Result changeInfo(RoleApiVo roleApi) {
        // 更新失败
        if (!tryif(() -> roleApiMapper.updateById(roleApi))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> roleApiBakMapper.insert(new RoleApiBak(roleApi.getId())));
        return Result.o(roleApi);
    }

    /**
     * 删除(需id,updateId)
     */
    @Transactional
    public Result deleteById(RoleApiVo roleApi) {
        // 删除失败
        if (!tryif(() -> roleApiMapper.deleteById(roleApi))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> roleApiBakMapper.insert(new RoleApiBak(roleApi.getId())));
        return Result.o(roleApi);
    }

}
