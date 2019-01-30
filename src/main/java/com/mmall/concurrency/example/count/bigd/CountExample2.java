package com.mmall.concurrency.example.count.bigd;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-01-29 下午3:36
 **/

@Slf4j
@ThreadSafe
public class CountExample2 {
    /**
     * 执行总数，5000次加一操作
     */
    private static final int REQUEST_TOTAL = 5000;
    /**
     * 同一时间的并发总数
     */
    private static final int CONCURRENCY_COUNT = 20;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        // 所有请求执行完毕之后统计我们的执行结果，因此这里使用的是 REQUEST_TOTAL
        final CountDownLatch countDownLatch = new CountDownLatch(REQUEST_TOTAL);
        final Semaphore semaphore = new Semaphore(CONCURRENCY_COUNT);
        // 初始化一个线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("con-%s").build();
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(CONCURRENCY_COUNT, MAXIMUM_POOL_SIZE, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30), threadFactory, new BlockingPolicy());

        for(int i = 0; i < REQUEST_TOTAL; i ++) {
            threadPoolExecutor.execute(() -> {
                try{
                    // 每个线程执行前需要尝试获取信号量（是否允许被执行，如果达到并发数，当前线程会被阻塞）
                    semaphore.acquire();
                    add();
                    // 执行完毕之后释放信号量
                    semaphore.release();
                }catch (Exception e){
                    log.error("caught some error", e);
                }
                // 线程执行完毕后执行次数 - 1
                countDownLatch.countDown();
                //countDownLatch.countDown();
            });
        }
        // 所有线程执行完毕之后，输出我们的结果
        countDownLatch.await();
        // 线程池执行完毕之后关闭线程池
        threadPoolExecutor.shutdown();
        log.info(">>>>>>>>>>>> : count:{}", count);
    }

    private static void add() throws InterruptedException{
        count.incrementAndGet();
    }
}
