package com.demo.service;

import java.util.List;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.demo.tool.Function;
import com.demo.tool.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <h1>基服务</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class BaseService {

    /**
     * try-if简化，不符合function条件的回滚
     *
     * @param function 要执行的函数
     */
    public static Result tryif(Function<Boolean> function) {
        return tryif(true, function);
    }

    /**
     * try-if简化
     *
     * @param rollbackIf 不符合function条件的是否回滚
     * @param function   要执行的函数
     */
    public static Result tryif(boolean rollbackIf, Function<Boolean> function) {
        try {
            if (!function.run()) {
                if (rollbackIf) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                return Result.e();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.e();
        }
        return Result.o();
    }

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
