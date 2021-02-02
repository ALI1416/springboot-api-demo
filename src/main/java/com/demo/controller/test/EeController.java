package com.demo.controller.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.excel.export.DemoData;
import com.demo.entity.excel.export.DemoDataListener;
import com.demo.util.EeUtils;

/**
 * <h1>EasyExcel api</h1>
 *
 * <p>
 * createDate 2021/02/02 16:49:06
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/EasyExcel")
@RestController
public class EeController {

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) {
        EeUtils.upload(file, DemoData.class, new DemoDataListener());
        return "success";
    }

    @GetMapping("download")
    public void download(HttpServletResponse response) {
        EeUtils.download(response, "测试", DemoData.class, data());
    }

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
}
