package com.mmall.concurrency.example.immutable.bigd;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-03-13 9:07 PM
 **/
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private static final Integer a = 1;
    private static final String b = "2";
    private static final Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        /**
         * 基础数据类型，不能改变它们的值，编译就会报错
         */
      //  a = 2;
      //  b = "3";
        /**
         * 引用类型，不允许指向其他对象
         */
        //map = Maps.newHashMap();
        /**
         * 引用对象可以改变对象的值但是，不允许改变引用指向另外一个对象
         */
        map.put(3, 5);

        log.info("3:{}",map.get(3));
    }

    /**
     * final 修饰一个方法出传入的变量，这个变量也是不允许修改的
     * 这样就不会在方法实现的时候被修改
     * @param a
     */
    private void test (final int a){
        //a = 1;
    }
}
