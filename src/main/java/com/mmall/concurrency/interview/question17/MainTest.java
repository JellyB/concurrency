package com.mmall.concurrency.interview.question17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-17 4:27 PM
 **/
public class MainTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int sum = 5;
        Chopstick[] chopsticks = new Chopstick[sum];
        for (int i = 0; i < sum; i++) {
            chopsticks[i] = new Chopstick(i);
        }
        for (int i = 0; i < sum; i++) {
            executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % sum], i));
        }
    }
}
