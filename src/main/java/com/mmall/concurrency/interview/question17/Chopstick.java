package com.mmall.concurrency.interview.question17;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-17 4:25 PM
 **/


// 筷子
public class Chopstick {
    // 筷子位置
    private int id;
    // 状态
    private boolean isUsed = false;

    public Chopstick(int id) {
        this.id = id;
    }

    // 拿取
    public synchronized void take() throws InterruptedException{
        while (isUsed){
            wait();
        }
        System.err.println(this + " 被使用");
        isUsed = true;
    }

    public synchronized  void drop(){
        isUsed = false;
        System.err.println(this + " 被放下");
        notifyAll();
    }

    @Override
    public String toString() {
        return "筷子[" + id + "]";
    }
}
