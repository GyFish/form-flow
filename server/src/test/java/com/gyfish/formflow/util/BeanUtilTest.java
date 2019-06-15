package com.gyfish.formflow.util;

import org.junit.Test;

import lombok.Data;

public class BeanUtilTest {

    @Test
    public void copyTest() {
        A1 a1 = new A1();
        a1.setA("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        long t1 = System.currentTimeMillis();
        System.out.println("start");

        for (int i = 0; i < 1e6; i++) {

            A2 a2 = BeanUtil.copy(a1, A2.class);
        }

        long t2 = System.currentTimeMillis();
        System.out.print(t2 - t1);
    }

    @Test
    public void copyAtoB() {
        A1 a1 = new A1("a", null);
        A2 a2 = new A2("a2", "zzzzzzzz");
        BeanUtil.copy(a1, a2, false);
        System.out.println(a2);
    }

}

@Data
class A1 {
    String a;
    String b;

    public A1() {
    }

    public A1(String a, String b) {
        this.a = a;
        this.b = b;
    }
}

@Data
class A2 {
    String a;
    String b;

    public A2() {
    }

    public A2(String a, String b) {
        this.a = a;
        this.b = b;
    }
}