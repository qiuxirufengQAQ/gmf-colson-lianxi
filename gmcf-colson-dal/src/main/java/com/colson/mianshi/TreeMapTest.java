package com.colson.mianshi;

import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
    @Test
    public void testTreeMap(){
        // treeMap需要初始化一个比较器，也可以不初始化
        Map<String,String> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // put操作会先判断有没有根Entry，如果没有，则将这个作为根节点，
        // 存在根Entry，则先判断是否存在比较器，如果没有，则用key的比较器来比较，小于0则取左子树，大于0则取右子树
        // 如果原来存在，则将原来的值return出去
        map.put("b","2");
        map.put("c","3");
        map.put("a","1");
        map.put("d","4");

        // 删除节点的时候，如果这个节点有左右子节点的时候，会优先将自己的左节点作为自己的节点，
        // 如果没有左节点，则将右节点作为自己的节点位置，将自己的节点位置删除
        // 完成之后按照红黑树的规则进行排序，调整整个二叉树
        map.remove("c");

        // 将root置为null，size=0,
        map.clear();

        // 查询的时候比较简单，如果大于根节点，则去右侧去取，否则去左侧取，相等则取当前节点，
        // 不断循环调用，如果到底都没有找到，则返回null
        map.get("a");

        // 看get的方法，返回的是不是空对象
        map.containsKey("a");

        // 通过遍历所有值进行比较判断，方法为先找到二叉树的最左侧的节点，然后找父节点的右节点的最左侧节点，没有则继续向父节点找，直到遍历整个二叉树
        map.containsValue("4");

        // 返回一个内部类对象，找具体的元素则去TreeMap中去找对应的数据
        map.keySet();
        // 返回一个内部类对象，找具体的元素则去TreeMap中去找对应的数据
        map.entrySet();

        // 获取所有的values跟containsValue用到的方法一样去遍历
        Collection<String> values = map.values();


    }
}
