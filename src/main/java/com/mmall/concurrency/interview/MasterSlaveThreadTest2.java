package com.mmall.concurrency.interview;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-09 11:05 AM
 **/
public class MasterSlaveThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        final Object object = new Object();
        new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50; i++) {
                    synchronized (object) {
                        for (int j = 0; j < 10; j++) {
                            System.out.println("SubThread:" + (i + 1) + "_" + (j + 1));
                        }
                        object.notify();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            synchronized (object) {
                //主线程让出锁，等待子线程唤醒
                for (int j = 0; j < 20; j++) {
                    System.out.println("   MainThread:" + (i + 1) + "_" + (j + 1));
                }
                object.notify();
                object.wait();
            }
        }
    }
}
