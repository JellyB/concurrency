package com.mmall.concurrency.example.singleton.bigd;

import com.mmall.concurrency.annoations.Recommend;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 描述：使用枚举实现单例模式，最安全的
 *
 * @author biguodong
 * Create time 2019-03-03 11:37 PM
 **/

@ThreadSafe
@Recommend
public class SingletonExample8 {

    /**
     * 私有的构造函数
     */
    private SingletonExample8(){

    }

    public static SingletonExample8 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample8 instance;

        /**
         *  jvm 保证这个方法只被调用一次，而且是绝对的
         */
        Singleton(){
            instance = new SingletonExample8();
        }

        public SingletonExample8 getInstance(){
            return instance;
        }
    }
}
