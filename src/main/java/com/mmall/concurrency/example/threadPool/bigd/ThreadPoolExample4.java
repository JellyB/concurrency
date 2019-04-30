package com.mmall.concurrency.example.threadPool.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-04-30 11:52 AM
 **/

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        log.info("new runnable task ");

        //提交一个 callable 任务并获取执行结果
        Future<String> future = scheduledExecutorService.schedule(new MyCallable(), 2, TimeUnit.SECONDS);
        String result = future.get();
        log.info(">>>>>>>>> result:{}", result);


        /*scheduledExecutorService.scheduleWithFixedDelay(() -> {
            log.info("new task, thread name:{}", Thread.currentThread().getName());
        }, 5, 2, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(20);
        scheduledExecutorService.shutdown();*/



        //提交一个任务 指定速率 执行一次
        /*scheduledExecutorService.scheduleAtFixedRate(() ->{
            log.info("new task, thread name:{}", Thread.currentThread().getName());
        }, 5, 2, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(20);
        scheduledExecutorService.shutdown();*/



        //提交一个任务 3 秒后执行
        /*scheduledExecutorService.schedule(() -> {
            log.info("new task, thread name:{}", Thread.currentThread().getName());
        }, 3, TimeUnit.SECONDS);*/

        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        }, 5000, 2000);*/
    }
}
