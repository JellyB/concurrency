/**
 * Copyright 2014-2025 JD.COM All Right Reserved.
 *
 * @file： ObjectTest.java   project: concurrency
 * @creator: biguodong
 * @date: 2020/9/28
 */

package com.mmall.concurrency.test;

public class ObjectTest {

    public static void main(String[] args) {
        Object[] values = new Object[3];
        String [] strs = new String[]{"23", "234213", "24123412"};
        values[0] = "12341234";
        values[1] = strs;

        Object object = values[1];
        String[] str_ = (String[]) object;
        System.err.println(str_);


    }

}
