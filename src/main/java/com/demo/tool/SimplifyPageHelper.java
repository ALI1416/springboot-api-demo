package com.demo.tool;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <h1>简化分页</h1>
 *
 * <p>
 * createDate 2020/11/28 17:21:13
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
public class SimplifyPageHelper {

    /**
     * 分页，返回List封装对象
     * 
     * @param <E>      返回的对象类型
     * @param pages    页码(从1开始)
     * @param rows     每页条数(为0时查询全部)
     * @param orderBy  排序(为null时默认排序)
     * @param function 要执行的查询语句
     */
    public static <E> List<E> paginationUnpack(int pages, int rows, String orderBy, Function<List<E>> function) {
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
        return function.run();
    }

    /**
     * 分页，返回PageInfo封装对象
     * 
     * @param <E>      返回的对象类型
     * @param pages    页码(从1开始)
     * @param rows     每页条数(为0时查询全部)
     * @param orderBy  排序(为null时默认排序)
     * @param function 要执行的查询语句
     */
    public static <E> PageInfo<E> pagination(int pages, int rows, String orderBy, Function<List<E>> function) {
        return new PageInfo<>(paginationUnpack(pages, rows, orderBy, function));
    }

}
