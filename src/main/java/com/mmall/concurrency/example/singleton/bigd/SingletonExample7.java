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
public class SingletonExample7 {

    /**
     * 私有构造函数
     */
    private SingletonExample7(){

    }

    private static SingletonExample7 instance = null;

    static{
        instance = new SingletonExample7();
    }

    public static SingletonExample7 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
