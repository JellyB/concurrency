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
public class SingletonExample2 {

    /**
     * 私有构造函数
     */
    private SingletonExample2(){

    }

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }
}
