package com.mmall.concurrency.example.aqs.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-04-11 6:29 PM
 **/
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("do something in callable");
            TimeUnit.SECONDS.sleep(5);
            return "future task done";
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        TimeUnit.SECONDS.sleep(1);
        String result = futureTask.get();
        log.info("result:{}", result);
    }
}
