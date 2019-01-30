package com.mmall.concurrency.example.sync.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述：synchronized 修饰静态方法 和 修饰一个类
 *
 * @author biguodong
 * Create time 2019-01-30 下午3:38
 **/
@Slf4j
public class SynchronizedStaticMethodAndClass {

    /**
     * synchronized 修饰一个静态方法
     * 作用的范围是整个静态方法，作用的对象是这个类的所有对象；
     */
    private synchronized static void synStaticMethod(String object){
        for (int i = 0; i < 10; i ++){
            log.info("synStaticMethod current value:{}, >>> current object:{}", i, object);
        }
    }

    /**
     * synchronized 修饰一个类
     * 作用的范围是 synchronized 后面阔气来的部分，作用的对象是这个类的所有对象
     */
    private static void syncClass(String object){
        synchronized (SynchronizedStaticMethodAndClass.class){
            for (int i = 0; i < 10; i ++){
                log.info("syncClass current value:{}, >>> current object:{}", i, object);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedStaticMethodAndClass synchronizedStaticMethodAndClass1 = new SynchronizedStaticMethodAndClass();
        SynchronizedStaticMethodAndClass synchronizedStaticMethodAndClass2 = new SynchronizedStaticMethodAndClass();
        ExecutorService pool =  new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(30));
        // 测试修饰静态方法
        //pool.execute( () -> synchronizedStaticMethodAndClass1.synStaticMethod("object 1"));
        //pool.execute( () -> synchronizedStaticMethodAndClass2.synStaticMethod("object 2"));
        // 测试修饰类
        pool.execute( () -> synchronizedStaticMethodAndClass1.syncClass("object 1"));
        pool.execute( () -> synchronizedStaticMethodAndClass2.syncClass("object 2"));
    }
}
