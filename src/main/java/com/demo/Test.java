package com.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import com.demo.entity.User;
import com.demo.result.BatchResult;
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

        BatchResult batchResult = new BatchResult();
        List<Pair<Boolean, Object>> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Pair<Boolean, Object> pair = new Pair<>(false, u);
            list.add(pair);
        }
        batchResult.setList(list);
        System.out.println(batchResult);
    }

}
