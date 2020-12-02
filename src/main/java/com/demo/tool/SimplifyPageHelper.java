package com.demo.tool;

import com.github.pagehelper.PageHelper;

/**
 * <h1>简化PageHelper分页排序类</h1>
 *
 * <p>
 * createDate 2020/12/02 20:12:00
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
public class SimplifyPageHelper {

    /**
     * 简化分页
     *
     * @param pages   页码
     * @param rows    每页条数(为0时查询全部)
     * @param orderBy 排序(为null时默认排序)
     */
    public static void pagination(int pages, int rows, String orderBy) {
        // orderBy == null && rows == 0：查询全部，默认排序
        if (orderBy == null) {
            if (rows != 0) {
                // 分页查询，默认排序
                PageHelper.startPage(pages, rows);
            }
        } else {
            if (rows == 0) {
                // 查询全部，排序
                PageHelper.orderBy(orderBy);
            } else {
                // 分页查询，排序
                PageHelper.startPage(pages, rows, orderBy);
            }
        }
    }
}
