package com.gyfish.formflow.util;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

public class JsonTest {

    @Test
    public void testParse() {

        String s = "1-1-";

        String r = JSON.parseObject(s, String.class);

        System.out.println(r);

    }

}
