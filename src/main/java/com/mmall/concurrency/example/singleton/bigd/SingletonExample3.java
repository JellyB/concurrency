package com.mmall.concurrency.example.singleton.bigd;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 描述：懒汉模式实现单例模式
 *
 * @author biguodong
 * Create time 2019-03-02 1:09 PM
 **/

@ThreadSafe
public class SingletonExample3 {

    /**
     * 私有构造函数，不允许外部访问
     */
    private SingletonExample3(){

    }

    /**
     * 单例对象
     */
    private static SingletonExample3 instance = null;

    /**
     * 静态工厂方法
     * @return
     */
    public synchronized static SingletonExample3 getInstance(){
        if(null == instance){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
