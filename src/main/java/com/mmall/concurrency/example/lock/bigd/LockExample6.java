package com.mmall.concurrency.example.lock.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-22 2:15 PM
 **/
@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        // 线程 1
        new Thread(() -> {
            try{
                reentrantLock.lock();
                log.info(Thread.currentThread().getName() + " waiting signal");     //1
                condition.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + " get signal");             //4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info(Thread.currentThread().getName() + " get lock");               //2
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            condition.signalAll();
            log.info(Thread.currentThread().getName() + " send signal ~");          //3
            reentrantLock.unlock();
        }).start();
    }
}
