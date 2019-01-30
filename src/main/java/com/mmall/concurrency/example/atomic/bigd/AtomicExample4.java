package com.mmall.concurrency.example.atomic.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-01-30 上午11:26
 **/
@Slf4j
public class AtomicExample4 {
    private static final AtomicReference<Integer> reference = new AtomicReference<>(1);

    public static void main(String[] args) {
        reference.compareAndSet(1, 2);//如果为1更新为2
        reference.compareAndSet(2, 3);//如果为2更新为3
        reference.compareAndSet(3, 5);//如果为3更新为5
        reference.compareAndSet(4, 3);//如果为4更新为3
        reference.compareAndSet(5, 3);//如果为5更新为3
        log.info("reference :{}", reference.get());
    }
}
