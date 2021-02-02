package com.demo.util;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;

/**
 * <h1>EasyExcel工具</h1>
 *
 * <p>
 * createDate 2021/02/02 14:56:48
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class EeUtils {

    /**
     * 写文件
     * 
     * @param filePath 文件路径
     * @param clazz    类
     * @param data     写入的数据
     */
    @SuppressWarnings("rawtypes")
    public static void write(String filePath, Class clazz, List data) {
        EasyExcel.write(filePath, clazz).registerWriteHandler(style1()).sheet("工作表1").doWrite(data);
    }

    /**
     * 读文件
     * 
     * @param pathName     文件的路径
     * @param clazz        类
     * @param readListener 读监听器
     */
    @SuppressWarnings("rawtypes")
    public static void read(String pathName, Class clazz, ReadListener readListener) {
        EasyExcel.read(pathName, clazz, readListener).sheet().doRead();
    }

    /**
     * 客户端下载文件
     * 
     * @param response HttpServletResponse
     * @param fileName 文件名
     * @param clazz    类
     * @param data     数据
     */
    @SuppressWarnings("rawtypes")
    public static void download(HttpServletResponse response, String fileName, Class clazz, List data) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), clazz).registerWriteHandler(style1()).sheet("工作表1")
                    .doWrite(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端上传文件
     * 
     * @param file         客户端上传过来的文件
     * @param clazz        类
     * @param readListener 读监听器
     */
    @SuppressWarnings("rawtypes")
    public static void upload(MultipartFile file, Class clazz, ReadListener readListener) {
        try {
            EasyExcel.read(file.getInputStream(), clazz, readListener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 样式1
     */
    public static HorizontalCellStyleStrategy style1() {
        /* 表头 */
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("宋体");
        headWriteFont.setFontHeightInPoints((short) 14);
        headWriteCellStyle.setWriteFont(headWriteFont);
        /* 内容 */
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定FillPatternType为FillPatternType.SOLID_FOREGROUND，不然无法显示背景颜色
        // 头默认了FillPatternType所以可以不指定
        // contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景颜色
        // contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        // 字体
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontName("宋体");
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
}
