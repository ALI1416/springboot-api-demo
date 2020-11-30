package com.demo.exception;

import com.demo.result.Result;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <h1>简化异常处理类</h1>
 *
 * <p>
 * createDate 2020/11/28 17:21:13
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
public class SimplifyException {

    /**
     * try-if简化，不符合function条件的回滚
     *
     * @param function 执行函数
     */
    public static Result tryif(SimplifyExceptionFunction function) {
        return tryif(true, function);
    }

    /**
     * try-if简化
     *
     * @param rollbackIf 不符合function条件的是否回滚
     * @param function   执行函数
     */
    public static Result tryif(boolean rollbackIf, SimplifyExceptionFunction function) {
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
}

