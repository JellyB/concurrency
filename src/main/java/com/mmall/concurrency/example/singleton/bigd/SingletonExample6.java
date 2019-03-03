package com.mmall.concurrency.example.singleton.bigd;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 描述：饿汉模式实现单例模式
 * 饿汉模式在类装载的时候创建
 *
 * @author biguodong
 * Create time 2019-03-02 1:20 PM
 **/
@ThreadSafe
public class SingletonExample6 {

    /**
     * 私有构造函数
     */
    private SingletonExample6(){

    }
    static{
        instance = new SingletonExample6();
    }

    private static SingletonExample6 instance = null;

    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.print(getInstance().hashCode());
        System.out.print(getInstance().hashCode());
    }
}
