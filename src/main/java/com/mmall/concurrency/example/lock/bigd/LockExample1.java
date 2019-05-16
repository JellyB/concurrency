package com.mmall.concurrency.example.lock.bigd;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：使用 synchronized 保证计数是线程安全的
 *
 * @author biguodong
 * Create time 2019-01-29 下午3:36
 **/

@Slf4j
@ThreadSafe
public class LockExample1 {
    /**
     * 执行总数，5000次加一操作
     */
    private static final int REQUEST_TOTAL = 5000;
    /**
     * 同一时间的并发总数
     */
    private static final int CONCURRENCY_COUNT = 20;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static  final Lock lock = new ReentrantLock();

    private static int count = 0;

    public static void main(String[] args) throws Exception{
        // 所有请求执行完毕之后统计我们的执行结果，因此这里使用的是 REQUEST_TOTAL
        final CountDownLatch countDownLatch = new CountDownLatch(REQUEST_TOTAL);
        final Semaphore semaphore = new Semaphore(CONCURRENCY_COUNT);
        // 初始化一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < REQUEST_TOTAL; i ++) {
            executorService.execute(() -> {
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
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info(">>>>>>>>>>>> : count:{}", count);
    }

    /**
     * 使用 synchronized 修饰静态方法
     * @throws InterruptedException
     */
    private static void add(){
        lock.lock();
        try{
            count ++;
        }finally {
            lock.unlock();
        }
    }
}
