package com.mmall.concurrency.example.deadLock.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 描述：一个简单的死锁类
 *
 * @author biguodong
 * Create time 2019-05-01 2:44 PM
 **/

@Slf4j
public class DeadLock implements Runnable{

    private int flag = 1;
    // 静态对象是类的所有对象，共享的
    public static final Object o1 = new Object(), o2 = new Object();
    @Override
    public void run() {
        log.info("flag:{}", flag);
        if(flag == 1){
            synchronized (o1){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (o2){
                    log.info("lock o2");
                }
            }
        }

        if(flag == 0){
            synchronized (o2){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (o1){
                    log.info("lock o1");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();
        td1.flag = 0;
        td2.flag = 1;
        // td1 和 td2 都是可运行状态，但JVM线程调度先运行那个是不知道的
        // td2 的 run() 可能在 td1的 run() 的之前运行
        new Thread(td1).start();
        new Thread(td2).start();
    }
}
