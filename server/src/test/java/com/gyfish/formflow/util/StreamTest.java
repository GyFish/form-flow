package com.gyfish.formflow.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void filter() {

        List<String> l1 = new ArrayList<>();

        l1.add("a");
        l1.add("b");
        l1.add("c");

        List l2 = l1.stream()
                .filter(e -> e.equals("a"))
                .collect(Collectors.toList());

        System.out.println(l2);
    }

    @Test
    public void test() {
        String s = "";
        String[] a = s.split("-");
        System.out.println(a);
    }

    @Test
    public void testContain() {
        String mobile = "1558***1111";
        String encryptMark = "*";
        if (!mobile.contains(encryptMark)) {
            System.out.println(1);
        }
    }

}
