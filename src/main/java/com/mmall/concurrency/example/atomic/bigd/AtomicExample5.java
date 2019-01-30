package com.mmall.concurrency.example.atomic.bigd;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-01-30 上午11:34
 **/
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static final AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    /**
     * 必须使用 volatile 修饰
     */
    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample5 atomicExample5 = new AtomicExample5();
        if(updater.compareAndSet(atomicExample5, 100, 110)){
            //返回一个boolean判断是否可以更新
            log.info("update to 110 success, count:{}", atomicExample5.getCount());
        }
        if(updater.compareAndSet(atomicExample5, 100, 110)){
            log.info("update to 110 success again, count:{}", atomicExample5.getCount());
        }else{
            log.error("update value failed");
        }
    }
}
