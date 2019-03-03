package com.mmall.concurrency.example.singleton.bigd;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 描述：懒汉模式实现单例模式 - 双重同步锁单例模式
 *
 * @author biguodong
 * Create time 2019-03-02 1:09 PM
 **/

@NotThreadSafe
public class SingletonExample4 {

    /**
     * 私有构造函数，不允许外部访问
     */
    private SingletonExample4(){

    }
    

    /**
     * 单例对象
     */
    private static SingletonExample4 instance = null;

    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample4 getInstance(){
        if(null == instance){ // 双重检测机制
            synchronized (SingletonExample4.class){ // 同步锁
                if(null == instance){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
