package com.demo.tool;

/**
 * 基于Twitter的Snowflake算法实现分布式高效有序ID生产黑科技(sequence)——升级版Snowflake
 *
 * <br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * <br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * <br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下START_TIME属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 *
 * 24 * 365) = 69<br>
 * <br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位dataCenterId和5位workerId<br>
 * <br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * <br>
 * <br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 * <p>
 * <p>
 * 特性：
 * 1.支持自定义允许时间回拨的范围<p>
 * 2.解决跨毫秒起始值每次为0开始的情况（避免末尾必定为偶数，而不便于取余使用问题）<p>
 * 3.解决高并发场景中获取时间戳性能问题<p>
 * 4.支撑根据IP末尾数据作为workerId
 * 5.时间回拨方案思考：1024个节点中分配10个点作为时间回拨序号（连续10次时间回拨的概率较小）
 *
 * @author lry
 * @version 3.0
 */
public final class Sequence {

    private static final Sequence worker = new Sequence(0);

    public static void main(String[] args) {
        ThreadPool.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("a" + worker.nextId());
            }
        });
        ThreadPool.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("b" + worker.nextId());
            }
        });
    }

    /**
     * 初始时间戳（如果时间回拨过，这个值会改变）
     **/
    private static long startTime = 1519740777809L;
    /**
     * 机器码占用的位数：{@value}
     **/
    private final static long MACHINE_ID_BITS = 8L;
    /**
     * 序列号占用的位数：{@value}
     **/
    private final static long SEQUENCE_BITS = 13L;
    /**
     * 机器码可以使用范围：{@value}
     **/
    private final static long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);
    /**
     * 机器码左移量：{@value}
     */
    private final static long MACHINE_ID_SHIFT = SEQUENCE_BITS;
    /**
     * 时间戳左移量：{@value}
     */
    private final static long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS;
    /**
     * 用mask防止溢出:位与运算保证计算的结果范围始终是：{@value}
     **/
    private final static long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    /**
     * 机器码
     */
    private final long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次生成的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 构造方法
     *
     * @param machineId 机器码
     */
    public Sequence(long machineId) {
        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new IllegalArgumentException("机器码machineId需要>=0并且<=" + MAX_MACHINE_ID + "。当前机器码为：" + MAX_MACHINE_ID);
        }
        this.machineId = machineId;
    }

    /**
     * 获取下一个序列号
     */
    public synchronized Long nextId() {
        // 当前时间戳
        long currentTimestamp = SystemClock.now();
        // 如果当前时间<上一次生成的时间戳，说明系统时钟回退过，我们去修改初始时间
        if (currentTimestamp < lastTimestamp) {
            // 校验时间偏移回拨量
            long offset = lastTimestamp - currentTimestamp;
        }

        // 同一毫秒内序列直接自增
        if (lastTimestamp == currentTimestamp) {
            long tempSequence = sequence + 1;
            // 通过位与运算保证计算的结果范围始终是 0-4095
            sequence = tempSequence & SEQUENCE_MASK;
            if (sequence == 0) {
                currentTimestamp = this.tilNextMillis(lastTimestamp);
            }
        }
        lastTimestamp = currentTimestamp;
        long currentOffsetTime = currentTimestamp - startTime;
        /*
         * 1.左移运算是为了将数值移动到对应的段(41、5、5，12那段因为本来就在最右，因此不用左移)
         * 2.然后对每个左移后的值(la、lb、lc、sequence)做位或运算，是为了把各个短的数据合并起来，合并成一个二进制数
         * 3.最后转换成10进制，就是最终生成的id
         */
        return (currentOffsetTime << TIMESTAMP_LEFT_SHIFT) |
                // 数据中心位
                (machineId << MACHINE_ID_SHIFT) |
                // 毫秒序列化位
                sequence;
    }

    /**
     * 保证返回的毫秒数在参数之后(阻塞到下一个毫秒，直到获得新的时间戳)——CAS
     *
     * @param lastTimestamp last timestamp
     * @return next millis
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = SystemClock.now();
        while (timestamp <= lastTimestamp) {
            // 如果发现时间回拨，则自动重新获取（可能会处于无限循环中）
            timestamp = SystemClock.now();
        }
        return timestamp;
    }

}