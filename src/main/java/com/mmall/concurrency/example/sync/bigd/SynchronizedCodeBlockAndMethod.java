package com.mmall.concurrency.example.sync.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 描述：synchronized 的使用
 *
 * @author biguodong
 * Create time 2019-01-30 下午2:03
 **/
@Slf4j
public class SynchronizedCodeBlockAndMethod {

    /**
     * synchronized 修饰一个代码块
     * 被修饰的代码块被称为同步语句块，作用的范围是大括号括起来的代码，作用的对象是调用这个代码块的对象
     */
    private void synCodeBlock(String object){
        synchronized (this){
            for (int i = 0; i < 10; i ++){
                log.info("synCodeBlock current value:{}, >>> current object:{}", i, object);
            }
        }
    }

    /**
     * synchronized 修饰一个方法
     * 被修饰的方法被称为同步方法，作用的范围是整个方法，作用的对象是调用这个方法的对象
     */
    private synchronized void syncMethod(String object){
        for (int i = 0; i < 10; i ++){
            log.info("syncMethod current value:{}, >>>> current object:{}", i, object);
        }
    }

    public static void main(String[] args) {
        SynchronizedCodeBlockAndMethod synchronizedCodeBlockAndMethod1 = new SynchronizedCodeBlockAndMethod();
        SynchronizedCodeBlockAndMethod synchronizedCodeBlockAndMethod2 = new SynchronizedCodeBlockAndMethod();
        ExecutorService pool =  new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(30));
        // 测试修饰同步代码块使用同一个对象
        //pool.execute( () -> synchronizedCodeBlockAndMethod1.synCodeBlock("object 1"));
        //pool.execute( () -> synchronizedCodeBlockAndMethod1.synCodeBlock("object 2"));
        // 测试修饰同步代码块使用不同的对象访问
        //pool.execute( () -> synchronizedCodeBlockAndMethod1.synCodeBlock("object 1"));
        //pool.execute( () -> synchronizedCodeBlockAndMethod2.synCodeBlock("object 2"));
        // 测试修饰方法使用同一个对象
        //pool.execute( () -> synchronizedCodeBlockAndMethod1.syncMethod("object 1"));
        //pool.execute( () -> synchronizedCodeBlockAndMethod1.syncMethod("object 2"));
        // 测试修饰方法使用不同的个对象
        pool.execute( () -> synchronizedCodeBlockAndMethod1.syncMethod("object 1"));
        pool.execute( () -> synchronizedCodeBlockAndMethod2.syncMethod("object 2"));

    }
}
