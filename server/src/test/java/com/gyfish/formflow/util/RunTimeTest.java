package com.gyfish.formflow.util;

import org.junit.Test;

public class RunTimeTest {


    /**
     * 获取系统核心数
     */
    @Test
    public void getProcessors() {
        Integer n = Runtime.getRuntime().availableProcessors();
        System.out.println(n);
    }

}
