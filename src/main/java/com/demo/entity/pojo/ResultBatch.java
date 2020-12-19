package com.demo.entity.pojo;

import com.demo.entity.BaseEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>批量返回结果实体</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
public class ResultBatch<T> extends BaseEntity {

    /**
     * 成功(全部都为true)
     */
    private boolean ok = true;
    /**
     * 全部的个数
     */
    private int total = 0;
    /**
     * 为true的个数
     */
    private int totalTrue = 0;
    /**
     * 为false的个数
     */
    private int totalFalse = 0;
    /**
     * 为true的列表
     */
    private final List<T> listTrue = new ArrayList<>();
    /**
     * 为false的列表
     */
    private final List<T> listFalse = new ArrayList<>();
    /**
     * 为true的列表的状态信息
     */
    private final List<String> listTrueMsg = new ArrayList<>();
    /**
     * 为false的列表的状态信息
     */
    private final List<String> listFalseMsg = new ArrayList<>();

    /**
     * 新增一个为true的对象
     *
     * @param t 对象
     */
    public void add(T t) {
        add(true, t, null);
    }

    /**
     * 新增一个为false的对象
     *
     * @param t   对象
     * @param msg 状态信息
     */
    public void add(T t, String msg) {
        add(false, t, msg);
    }

    /**
     * 新增一个对象
     *
     * @param ok  true/false
     * @param t   对象
     * @param msg 状态信息
     */
    public void add(boolean ok, T t, String msg) {
        if (ok) {
            listTrue.add(t);
            listTrueMsg.add(msg);
            totalTrue += 1;
        } else {
            listFalse.add(t);
            listFalseMsg.add(msg);
            totalFalse += 1;
            this.ok = false;
        }
        total += 1;
    }
}
