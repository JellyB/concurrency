package com.mmall.concurrency.example.threadPool.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-04-30 2:50 PM
 **/
@Slf4j
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        log.info("callable task run...");
        TimeUnit.SECONDS.sleep(10);
        return "ok~";
    }
}
