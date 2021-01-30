package com.demo;

import com.alibaba.excel.EasyExcel;
import com.demo.entity.excel.export.DemoData;
import com.demo.entity.excel.export.DemoDataListener;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>TODO EasyExcelExportTest Title</h1>
 *
 * <p>
 * TODO EasyExcelExportTest Description
 * </p>
 *
 * <p>
 * createDate 2021/01/30 14:50:20
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class EasyExcelExportTest {

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

    @Test
    public void simpleWrite() {
        EasyExcel.write("D:/test.xlsx", DemoData.class).sheet("模板").doWrite(data());
    }

    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read("D:/test.xlsx", DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
