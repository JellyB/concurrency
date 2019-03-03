package com.mmall.concurrency.example.singleton.bigd;


import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 描述：懒汉模式实现单例模式 - 双重同步锁单例模式
 *
 * @author biguodong
 * Create time 2019-03-02 1:09 PM
 **/

@ThreadSafe
public class SingletonExample5 {

    /**
     * 私有构造函数，不允许外部访问
     */
    private SingletonExample5(){

    }

    /**
     * 1. memory = allocate() 分配对象的内存空间；
     * 2. ctorInstance() 初始化对象；
     * 3. instance = memory 设置 instance 指向刚分配的n内存
     *
     */

    /**
     * volatile + 双重同步锁，禁止指令重排
     */
    private volatile static SingletonExample5 instance = null;

    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample5 getInstance(){
        if(null == instance){ // 双重检测机制     // 线程 B
            synchronized (SingletonExample5.class){ // 同步锁
                if(null == instance){
                    instance = new SingletonExample5(); // 线程 A 执行到了指令重排后的第 3 步
                }
            }
        }
        return instance;
    }
}
