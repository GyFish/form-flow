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

    @SuppressWarnings("unchecked")
    public <T> T getByQuery(Object v, Class<T> type) {


        if (v == null) {
            return null;
        }

        if (type.equals(String.class)) {
            return (T) String.valueOf(v);
        }

        if (type.equals(Integer.class)) {
            return (T) Integer.valueOf(String.valueOf(v));
        }

        if (type.equals(Long.class)) {
            return (T) Long.valueOf(String.valueOf(v));
        }

        return JSON.parseObject(JSON.toJSONString(v), type);
    }

    @Test
    public void testType() {
        int v = getByQuery(null, Integer.class);
        System.out.println(v);
    }

}
