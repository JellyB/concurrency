package com.mmall.concurrency.example.immutable.bigd;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mmall.concurrency.annoations.ThreadSafe;

import java.util.List;

@ThreadSafe
public class ImmutableExample3 {

    /**
     * of 方法支持可变长度的数据类型接入， 如果元素个数超过 12 使用可变长度的参数接收
     */
    private static final ImmutableList immutableList = ImmutableList.of(1, 2, 3);

    private static final List<Integer> list = ImmutableList.of(1, 2, 3);

    private static final ImmutableSet set = ImmutableSet.copyOf(immutableList);

    private static final ImmutableMap<Integer, Integer> map = ImmutableMap.of(1,2,3,4,5,6);

    private static final ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1,2)
            .put(3,4)
            .put(5,6)
            .build();

    /**
     * immutableList 显示方法被废弃，不允许使用， set 同样
     *
     * @param args
     */
    public static void main(String[] args) {
        list.add(4);
        immutableList.add(4);
        set.add(4);

        map.put(1,2);

        map2.put(3,4);

        System.out.println(map.get(3));
    }

}
