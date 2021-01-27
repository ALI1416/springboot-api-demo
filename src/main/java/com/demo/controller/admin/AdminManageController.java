package com.demo.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.ResultCodeEnum;
import com.demo.controller.BaseController;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.AdminVo;
import com.demo.service.AdminService;
import com.demo.tool.Id;
import com.demo.util.AuthUtils;
import com.demo.util.EncoderUtils;

import lombok.AllArgsConstructor;

/**
 * <h1>管理员管理api</h1>
 *
 * <p>
 * createDate 2021/01/23 19:10:15
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("admin/admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminManageController extends BaseController {

    private final HttpServletRequest request;
    private final AdminService adminService;

    /**
     * 新增用户(需account,pwd)
     */
    @Auth
    @PostMapping("/insert")
    public Result insert(@RequestBody AdminVo admin) {
        if ((existEmpty(admin.getAccount(), admin.getPwd())) || admin.getPwd().length() != 32) {
            return Result.e1();
        }
        // 用户已存在
        if (adminService.existAccount(admin.getAccount())) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        admin.setId(Id.next());
        admin.setPwd(EncoderUtils.bCrypt(admin.getPwd()));
        admin.setCreateId(AuthUtils.getUserId(request));
        return adminService.insert(request, admin);
    }

    /**
     * 修改信息(需id;至少一个account,pwd,name,comment)
     */
    @Auth
    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody AdminVo admin) {
        if (admin.getPwd() != null) {
            admin.setPwd(EncoderUtils.bCrypt(admin.getPwd()));
        }
        return adminService.changeInfo(admin);
    }

    /**
     * 删除(需id)
     */
    @Auth
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable long id) {
        return adminService.deleteById(id);
    }

    /**
     * 查看信息(需id)
     */
    @Auth
    @PostMapping("/showInfo/{id}")
    public Result showInfo(@PathVariable long id) {
        AdminVo admin = adminService.findById(id);
        admin.setPwd(null);
        return Result.o(admin);
    }

}
