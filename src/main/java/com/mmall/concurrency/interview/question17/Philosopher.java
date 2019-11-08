package com.mmall.concurrency.interview.question17;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-17 4:26 PM
 **/
public class Philosopher implements Runnable{


    private Chopstick left;
    private Chopstick right;
    private int id;
    private Random random = new Random();

    public Philosopher(Chopstick left, Chopstick right, int id) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                think();
                System.err.println(this + " 想吃饭");
                eat();
            }catch (InterruptedException e){
                System.err.println(this + "InterruptedException");
            }
        }

    }

    // 思考
    private void think() throws InterruptedException{
        System.err.println(this + " 思考...");
        TimeUnit.MILLISECONDS.sleep(random.nextInt(10) * 100);
    }

    // 吃饭
    private void eat() throws InterruptedException{
        left.take();
        right.take();
        System.err.println(this + " 正在吃饭...");
        TimeUnit.MILLISECONDS.sleep(random.nextInt(20) * 100);
        left.drop();
        right.drop();
    }


    @Override
    public String toString() {
        return "哲学家[" + id + "]";
    }
}
