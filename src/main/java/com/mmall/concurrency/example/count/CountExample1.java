package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class CountExample1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        // 初始化一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 所有请求执行完毕之后统计我们的执行结果，因此这里使用的是 clientTotal
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    // 每个线程执行前需要尝试获取信号量（是否允许被执行，如果达到并发数，当前线程会被阻塞）
                    semaphore.acquire();
                    add();
                    // 执行完毕之后释放信号量
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                // 线程执行完毕后执行次数 - 1
                countDownLatch.countDown();
            });
        }
        // 所有线程执行完毕之后，log 我们的结果
        countDownLatch.await();
        // 线程池执行完毕之后关闭线程池
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count++;
    }
}
