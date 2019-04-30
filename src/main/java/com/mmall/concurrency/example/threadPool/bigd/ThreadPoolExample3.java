package com.mmall.concurrency.example.threadPool.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-04-30 11:52 AM
 **/

@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 提交 10 个任务并打印，相当于 顺序 执行
        for(int i = 0 ; i < 10; i ++){
            final int index = i;
            executorService.submit(() -> {
                log.info("task no:{}, thread name:{}", index, Thread.currentThread().getName());
            });
        }
    }
}
