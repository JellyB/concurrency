package com.mmall.concurrency.example.singleton.bigd;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 描述：懒汉模式实现单例模式
 *
 * @author biguodong
 * Create time 2019-03-02 1:09 PM
 **/

@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有构造函数，不允许外部访问
     */
    private SingletonExample1(){

    }

    /**
     * 单例对象
     */
    private static SingletonExample1 instance = null;

    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample1 getInstance(){
        if(null == instance){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
