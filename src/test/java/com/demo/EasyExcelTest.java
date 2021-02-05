package com.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.demo.entity.excel.UserExport;
import com.demo.entity.excel.UserImport;
import com.demo.util.EeUtils;

public class EasyExcelTest {


//    @Test
    void importTemplate() {
        EeUtils.write("D:/模板1.xlsx", UserImport.class, null);
    }

//    @Test
    void importExcel() {
        List<UserImport> list = new ArrayList<>();
        EeUtils.read("D:/模板.xlsx", UserImport.class, list);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    void exportExcel() {
        List<UserExport> list = new ArrayList<>();
        UserExport u1 = new UserExport();
        u1.setAccount("account");
        u1.setHasPwd("无密码");
        u1.setName("name");
        u1.setGender("男");
        u1.setYear(1998);
        u1.setProfile("简介简介简介简介简介简介简介简介简介简介简介简介");
        u1.setComment("备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注");
        u1.setEmail("1416978277@qq.com");
        u1.setBindQq("未绑定");
        u1.setQqName("");
        u1.setIsDelete("");
        u1.setCreateName("admin");
        u1.setCreateTime(new Date());
        u1.setUpdateName("");
        u1.setUpdateTime(new Date());
        u1.setVersion(2);
        list.add(u1);
        EeUtils.write("D:/导出.xlsx", UserExport.class, list);
    }
}
