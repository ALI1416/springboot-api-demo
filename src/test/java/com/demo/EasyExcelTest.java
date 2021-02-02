package com.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.demo.entity.excel.export.DemoData;
import com.demo.entity.excel.export.DemoDataListener;
import com.demo.util.EeUtils;

/**
 * <h1>EasyExcelExportTest</h1>
 *
 * <p>
 * createDate 2021/01/30 14:50:20
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class EasyExcelTest {

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

//    @Test
    public void simpleWrite() {
        EeUtils.write("D:/test.xlsx", DemoData.class, data());
    }

    @Test
    public void simpleRead() {
        EeUtils.read("D:/test.xlsx", DemoData.class, new DemoDataListener());
    }
}
