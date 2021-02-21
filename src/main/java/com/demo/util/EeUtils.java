package com.demo.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
     * @param data     写入Excel的数据
     */
    public static <T> void write(String filePath, Class<?> clazz, List<T> data) {
        EasyExcel.write(filePath, clazz).registerWriteHandler(style1()).sheet("工作表1").doWrite(data);
    }

    /**
     * 读文件
     *
     * @param filePath 文件路径
     * @param clazz    类
     * @param data     读出Excel的数据
     */
    public static <T> void read(String filePath, Class<?> clazz, List<T> data) {
        AllDataListener<T> listener = new AllDataListener<>();
        EasyExcel.read(filePath, clazz, listener).sheet().doRead();
        data.addAll(listener.getData());
    }

    /**
     * 客户端下载文件
     *
     * @param response HttpServletResponse
     * @param fileName 文件名(自动追加yyyyMMddHHmmssSSS格式的时间，以及后缀.xlsx)
     * @param clazz    类
     * @param data     要生成的Excel数据
     */
    public static <T> void download(HttpServletResponse response, String fileName, Class<?> clazz, List<T> data) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = fileName + DateUtils.getDatetime("yyyyMMddHHmmssSSS");
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), clazz)//
                    .registerWriteHandler(style1())//
                    .sheet("工作表1")//
                    .doWrite(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端上传文件
     *
     * @param file  客户端上传过来的MultipartFile文件实体
     * @param clazz 类
     * @param data  解析出的Excel数据
     */
    public static <T> void upload(MultipartFile file, Class<?> clazz, List<T> data) {
        AllDataListener<T> listener = new AllDataListener<>();
        try {
            EasyExcel.read(file.getInputStream(), clazz, listener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.addAll(listener.getData());
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

/**
 * 读取所有数据监听器
 */
class AllDataListener<T> extends AnalysisEventListener<T> {

    private final List<T> data = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        this.data.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    /**
     * 获取到所有数据
     */
    public List<T> getData() {
        return data;
    }

}
