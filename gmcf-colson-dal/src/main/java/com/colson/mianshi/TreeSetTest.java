package com.colson.mianshi;

import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class TreeSetTest {

    @Test
    public void testTreeSet(){
        // 调用TreeMap的构造方法
        TreeSet<String> set = new TreeSet<>();
        // 调用treeMap的put方法
        set.add("a");
        // 调用treeMap的containsKey方法
        set.contains("a");
        // 调用treeMap的remove方法
        set.remove("a");
        // 返回比较器
        Comparator<? super String> comparator = set.comparator();

        // 返回最左侧的元素（最小值）
        set.first();
        // 返回treeMap最右侧的元素（最大值）
        set.last();

        // 返回这个键，或者返回小于这个的最大值
        set.floor("a");

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

    }
}
