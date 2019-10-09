package com.mmall.concurrency.interview;

import java.util.concurrent.Semaphore;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-09 11:05 AM
 **/
public class MasterSlaveThreadTest {
    static volatile boolean mainFlag = false;
    public static void main(String[] args) throws InterruptedException {

        int mainCount = 0;
        new Thread(() -> {
            int threadCount = 0;
            while(threadCount < 50){
                if(!mainFlag){
                    for (int j = 0; j < 10; j++) {
                        System.out.println("SubThread:" + (threadCount + 1) + "_" + (j + 1));
                    }
                    mainFlag = true;
                    threadCount ++;
                }
            }
        }).start();

        while (mainCount < 50){
            if(mainFlag){
                for (int j = 0; j < 20; j++) {
                    System.out.println("     MainThread:" +  (mainCount + 1)  + "_" + (j + 1));
                }
                mainFlag = false;
                mainCount ++;
            }
        }
    }
}
