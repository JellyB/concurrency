package com.mmall.concurrency.example.lock.bigd;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：使用 synchronized 保证计数是线程安全的
 *
 * @author biguodong
 * Create time 2019-01-29 下午3:36
 **/

@Slf4j
@ThreadSafe
public class LockExample3 {

    private final Map<String, Data> map = new TreeMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }

    }

    public Data put(String key, Data data){
        writeLock.lock();
        try {
            return map.put(key, data);
        }finally {
            writeLock.unlock();
        }
    }

    class Data{

    }
}
