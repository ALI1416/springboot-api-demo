package com.demo;

import com.demo.a.A;
import com.demo.a.IdWorker;
import com.demo.a.SystemClock;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.UserVo;

/**
 * <h1>测试类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Test {

    public static void main(String[] args) {
        User u = new User();
        u.setId(0);
        u.setAccount("root");
        u.setName("root");
        u.setGender(0);
        u.setYear(0);
        System.out.println(u);

        UserVo uv = new UserVo();
        uv.setId(1);
        uv.setAccount("admin");
        uv.setName("ck");
        uv.setGender(1);
        uv.setYear(1998);
        uv.setYearNot(0);
        uv.setYearEnd(2000);
        System.out.println(uv);

        Result r1 = Result.o(ResultCodeEnum.OK);
        System.out.println(r1.isOk());
        Result r2 = Result.e(ResultCodeEnum.ERROR);
        System.out.println(r2.isOk());
        Result r3 = Result.e(ResultCodeEnum.USER_ACCOUNT_FORBIDDEN, "哈哈哈哈", u);
        System.out.println(r3);
        User ru = (User) r3.getData();
        System.out.println(ru);

        ResultBatch<User> batchResult = new ResultBatch<>();
        batchResult.add(u);
        batchResult.add(false, u, "fff");
        batchResult.add(u);
        batchResult.add(false, u, "aaa");
        System.out.println(batchResult);
        System.out.println(batchResult.isOk());

        System.out.println(IdWorker.getId());
    }

}
