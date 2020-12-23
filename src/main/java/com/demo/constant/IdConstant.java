package com.demo.constant;

import com.demo.property.IdAppYml;
import com.demo.property.Yml;

/**
 * <h1>高性能Id生成器常量</h1>
 *
 * <p>
 * createDate 2020/12/23 15:51:32
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class IdConstant {

    private static final IdAppYml ID = Yml.idAppYml;

    /**
     * 机器码
     */
    public final static long MACHINE_ID = ID.getMachineId();
    /**
     * 机器码位数
     */
    public final static long MACHINE_BITS = ID.getMachineBits();
    /**
     * 序列号位数
     */
    public final static long SEQUENCE_BITS = ID.getSequenceBits();
}
