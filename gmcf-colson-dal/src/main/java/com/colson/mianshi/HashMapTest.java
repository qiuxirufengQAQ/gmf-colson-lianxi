package com.colson.mianshi;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    @Test
    public void testHashMap(){
        // 默认负载因子为0.75
        Map<String,String> map = new HashMap<>();
        // 根据传入的大小的进行初始化容器，初始化大小为 大于输入值的 最小的2的指数幂
        Map<String,String> map2 = new HashMap<>(8);
        // 负载因为为传入的值
        Map<String,String> map3 = new HashMap<>(8,0.78f);

        map.put("a","1");
        // put重复的值时，会将旧值返回
        // put时，会先计算该对象值的hash值，如果该对象为null，则hash值为0
        // Map底层为Node数组，索引位置为hash & length-1，也就是说取hash的最后几位，
        // 对应哪个数组的位置就放到哪个位置上，如果这个位置上没有其他的对象，则new 一个Node，并且这个Node的next为null
        // 如果这个位置已经有其他对象了，那么就比较一下这个对象的hash和key是否和新key对象相等，相等的话，就说明存放的是相同的key，将原来的value返回
        // 不断找这个Node的next，直到找到为null的，新建一个Node，如果节点>=7，则转成二叉树
        String a = map.put("a", "2");
        // 扩容机制，如果增加这个元素后已经过了门槛（>=容量*负载因子），就进行扩容操作
        // 扩容的逻辑为新生成一个数组，长度为原数组的2倍，遍历原数组，
        // 将每个元素复制到新的数组上（复制的时候，需要考虑原数组的位置上是否为空，存在next节点，二叉树Node）
        // 如果没有重复，则返回null
        String b = map.put("b", "2");
        // 获取map的值时，通过hash(key)，确认在table上的位置，如果没有next节点，就返回本节点，
        // 如果有则继续判断是二叉树还链表，根据key去判断元素相等则返回该元素
        String a1 = map.get("a");
        // 删除首先确认找到Node的位置，如果在数组上且没有next，则将其设为null，如果是链表，
        // 则将前一个指针指向该节点的下一个即可，如果是二叉树，则去掉二叉树该节点
        String a2 = map.remove("a");
        // 清空操作会将size设为0，遍历数组设为空
        map.clear();
        System.out.println(a);
        System.out.println(b);

    }
}
