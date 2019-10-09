package com.mmall.concurrency.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-08 1:49 PM
 **/
public class FinallyTest {

    public static void main(String[] args) {
        //System.err.println("test1:" + test1());
        //System.err.println("test2:" + test2());
        System.err.println(test3().get("KEY"));

    }

    private static int test1() {
        int i = 1;
        try {
            System.out.println("try...");
            return i += 10;
        } catch (Exception e) {
            System.out.println("catch...");
        } finally {
            i++;
            System.out.println("finally...");
            System.out.println("i=" + i);
        }
        return i;
    }

    private static int test2() {
        int i = 1;
        try {
            System.out.println("try...");
            return i += 10;
        } catch (Exception e) {
            System.out.println("catch...");
        } finally {
            i++;
            System.out.println("finally...");
            System.out.println("i=" + i);
            return i;
        }
    }

    private static Map<String, String> test3() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("KEY", "INIT");
        try {
            System.out.println("try...");
            map.put("KEY", "TRY");
            return map;
        } catch (Exception e) {
            System.out.println("catch...");
            map.put("KEY", "CATCH");
        } finally {
            System.out.println("finally...");
            map.put("KEY", "FINALLY");
            map = null;
        }
        return map;
    }

    private void test
            (){
        String s1 = "hello world";
        String s2 = "hello world";
        String s3 = new String("hello world");
        String s4 = s3.intern();
        System.err.println("s1==s2?"+(s1==s2));//s1==s2?true
        System.err.println("s1==s3?"+(s1==s3));//s1==s3?false
        System.err.println("s1==s4?"+(s1==s4));//s1==s4?true
        System.err.println(s1=="hello"+" world");//true
       // System.err.println(s1=="hello"+s5);//false
      //  System.err.println(s1==("hello"+s5).intern());//true
    }
}
