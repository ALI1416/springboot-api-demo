package com.demo.property;

/**
 * <h1>高性能Id生成器属性</h1>
 *
 * <p>
 * createDate 2020/12/23 15:51:32
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class IdProperty {

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
