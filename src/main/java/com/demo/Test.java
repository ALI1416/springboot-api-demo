package com.demo;

import com.demo.constant.ResultCodeEnum;
import com.demo.entity.User;
import com.demo.tool.ResultBatch;
import com.demo.tool.Result;
import com.demo.vo.UserVo;

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

        ResultBatch<User> batchResult = new ResultBatch<>();
        batchResult.add(u);
        batchResult.add(u);
        batchResult.add(false, u);
        batchResult.add(false, u);
        System.out.println(batchResult);
        System.out.println(batchResult.isOk());
    }

}
