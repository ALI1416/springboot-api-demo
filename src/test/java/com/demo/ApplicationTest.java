package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.property.IdProperty;

/**
 * <h1>ApplicationTest</h1>
 *
 * <p>
 * createDate 2021/02/17 12:37:36
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@SpringBootTest
public class ApplicationTest {

    @Test
    void contextLoads() {
        System.out.println(IdProperty.MACHINE_BITS);
    }

}
