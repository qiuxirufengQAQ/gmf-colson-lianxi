package com.colson.mianshi;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {
    @Test
    public void testHashSet(){
        // 初始化的时候会new 一个HashMap
        Set<String> set = new HashSet<>();
        // 这两个参数作为HashMap的初始化参数，分别为初始化大小和负载因子
        Set<String> set2 = new HashSet<>(9,0.8f);
        // 调用HashMap.put("a",object),其中object为hashSet中的一个固定对象
        set.add("a");
        // 调用HashMap.containsKey("a");
        set.contains("a");
        // 调用map.clear();
        set.clear();
        // map.isEmpty();
        set.isEmpty();
        // map.size();
        set.size();
    }
}
