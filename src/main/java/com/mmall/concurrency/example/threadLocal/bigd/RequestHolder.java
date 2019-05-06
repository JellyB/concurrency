package com.mmall.concurrency.example.threadLocal.bigd;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-06 5:17 PM
 **/
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    //比如在filter里面调用add存储信息
    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    /**
     * 移除变量信息
     * 如果不调用remove方法会导致内存泄漏，数据永远不会释放掉
     * private final static ThreadLocal<Long> 会一直伴随着我们项目，只有在项目重启的时候里面存储的信息才会释放掉
     */
    public static void remove(){
        requestHolder.remove();
    }
}
