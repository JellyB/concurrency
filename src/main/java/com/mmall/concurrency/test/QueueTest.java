/**
 * Copyright 2014-2025 JD.COM All Right Reserved.
 *
 * @file： QueueTest.java   project: concurrency
 * @creator: biguodong
 * @date: 2020/9/27
 */

package com.mmall.concurrency.test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

public class QueueTest {

    private static final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10240);

    public static void main(String[] args) {


        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024));
        pool.execute(()-> {
            while (true){
                try{
                    for(int i = 0; i < 100; i ++){
                        queue.offer(i + "", 1000 , TimeUnit.MICROSECONDS);
                    }
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        pool.execute(()-> {
            while (true){
                try{
                    String value = queue.poll(5000, TimeUnit.MICROSECONDS);

                    System.err.println(value);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
