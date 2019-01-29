package com.mmall.concurrency.example.count.bigd;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-01-29 下午4:27
 **/
public class BlockingPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try{
            executor.getQueue().put(r);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
