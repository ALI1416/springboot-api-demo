package com.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h1>高性能Id生成器配置</h1>
 *
 * <p>
 * createDate 2020/12/23 15:45:55
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "id", ignoreInvalidFields = true)
@Component
@Data
public class IdAppYml {
    private long machineId = 0L;
    private long machineBits = 8L;
    private long sequenceBits = 12L;
}
