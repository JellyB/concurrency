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
     */
    private synchronized void syncMethod(){
        for (int i = 0; i < 10; i ++){
            log.info("syncMethod current value:{}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedCodeBlockAndMethod synchronizedCodeBlockAndMethod1 = new SynchronizedCodeBlockAndMethod();
        SynchronizedCodeBlockAndMethod synchronizedCodeBlockAndMethod2 = new SynchronizedCodeBlockAndMethod();
        ExecutorService pool =  new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(30));
        pool.execute( () -> synchronizedCodeBlockAndMethod1.synCodeBlock("object 1"));
        pool.execute( () -> synchronizedCodeBlockAndMethod2.synCodeBlock("object 2"));
        //pool.execute( () -> method.syncMethod());
        //pool.execute( () -> method.syncMethod());

    }
}
