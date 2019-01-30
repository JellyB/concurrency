package com.mmall.concurrency.example.atomic.bigd;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import com.mmall.concurrency.example.count.bigd.BlockingPolicy;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-01-29 下午3:36
 **/

@Slf4j
@ThreadSafe
public class AtomicExample6 {

    private static final int REQUEST_TOTAL = 5000;
    private static final int CONCURRENCY_COUNT = 100;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static AtomicBoolean checkHappened = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(REQUEST_TOTAL);
        final Semaphore semaphore = new Semaphore(CONCURRENCY_COUNT);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("con-%s").build();
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(CONCURRENCY_COUNT, MAXIMUM_POOL_SIZE, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30), threadFactory, new BlockingPolicy());
        for(int i = 0; i < REQUEST_TOTAL; i ++) {
            threadPoolExecutor.execute(() -> {
                try{
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (Exception e){
                    log.error("caught some error", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        threadPoolExecutor.shutdown();
        log.info(">>>>>>>>>>>> : checkHappened:{}", checkHappened.get());
    }

    private static void test() throws InterruptedException{
        if(checkHappened.compareAndSet(false, true)){
            log.info("value changed");
        }
    }
}
