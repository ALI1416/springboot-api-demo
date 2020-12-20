package com.demo.service;

import com.demo.constant.Constant;
import com.demo.entity.BaseEntity;
import com.demo.entity.pojo.Result;
import com.demo.tool.Function;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

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
     * 分页，返回List封装对象<br>
     * 
     * @param <E>        返回的对象类型
     * @param baseEntity 基实体(从中获取分页参数)<br>
     *                   默认分页：baseEntity==null或pages==null或rows==null<br>
     *                   不分页：enablePage==false<br>
     * @param function   要执行的查询语句
     */
    public static <E> List<E> paginationUnpack(BaseEntity baseEntity, Function<List<E>> function) {
        /* baseEntity==null：默认分页 */
        if (baseEntity == null) {
            baseEntity = new BaseEntity();
            baseEntity.setEnablePage(Constant.PAGE_DEFAULT_ENABLE);
            baseEntity.setPages(Constant.PAGE_DEFAULT_PAGES);
            baseEntity.setRows(Constant.PAGE_DEFAULT_ROWS);
            baseEntity.setOrderBy(Constant.PAGE_DEFAULT_ORDER_BY);
        }
        // 是否启用分页
        Boolean enablePage = baseEntity.getEnablePage();
        // 页码(从1开始)
        Integer pages = baseEntity.getPages();
        // 每页条数(为0时查询全部)
        Integer rows = baseEntity.getRows();
        /* 页码或每页条数为空，不需要分页 */
        if (pages == null || rows == null) {
            return function.run();
        }
        // 排序(为null时默认排序)
        String orderBy = baseEntity.getOrderBy();
        /* orderBy == null && rows == 0 : 查询全部，默认排序 */
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
     * @param <E>        返回的对象类型
     * @param baseEntity 基实体(从中获取分页参数)
     * @param function   要执行的查询语句
     */
    public static <E> PageInfo<E> pagination(BaseEntity baseEntity, Function<List<E>> function) {
        return new PageInfo<>(paginationUnpack(baseEntity, function));
    }

}
