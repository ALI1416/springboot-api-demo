package com.demo.service;

import com.demo.dao.RoleApiBakDao;
import com.demo.dao.RoleApiDao;
import com.demo.entity.po.RoleApiBak;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.RoleApiVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleApiService extends BaseService {

    private final RoleApiDao roleApiDao;
    private final RoleApiBakDao roleApiBakDao;

    /**
     * 存在id
     */
    public boolean existId(long id) {
        RoleApiVo roleApi = new RoleApiVo();
        roleApi.setId(id);
        return roleApiDao.existUniqueKey(roleApi);
    }

    /**
     * 存在name
     */
    public boolean existName(String name) {
        RoleApiVo roleApi = new RoleApiVo();
        roleApi.setName(name);
        return roleApiDao.existUniqueKey(roleApi);
    }

    /**
     * 查找，通过id
     */
    public RoleApiVo findById(long id) {
        RoleApiVo roleApi = new RoleApiVo();
        roleApi.setId(id);
        return roleApiDao.findByUniqueKey(roleApi);
    }

    /**
     * 查找，通过name
     */
    public RoleApiVo findByName(String name) {
        RoleApiVo roleApi = new RoleApiVo();
        roleApi.setName(name);
        return roleApiDao.findByUniqueKey(roleApi);
    }

    /**
     * 新增(需id,name,createId)
     */
    @Transactional
    public Result insert(HttpServletRequest request, RoleApiVo roleApi) {
        // 插入失败
        if (!tryif(() -> roleApiDao.insert(roleApi))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> roleApiBakDao.insert(new RoleApiBak(roleApi.getId())));
        return Result.o();
    }

    /**
     * 修改信息(需id,updateId,name)
     */
    @Transactional
    public Result changeInfo(RoleApiVo roleApi) {
        // 更新失败
        if (!tryif(() -> roleApiDao.updateById(roleApi))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> roleApiBakDao.insert(new RoleApiBak(roleApi.getId())));
        return Result.o(roleApi);
    }
    
    /**
     * 删除(需id,updateId)
     */
    @Transactional
    public Result deleteById(RoleApiVo roleApi) {
        // 删除失败
        if (!tryif(() -> roleApiDao.deleteById(roleApi))) {
            return Result.e();
        }
        // 备份
        recordBak(() -> roleApiBakDao.insert(new RoleApiBak(roleApi.getId())));
        return Result.o(roleApi);
    }

}
