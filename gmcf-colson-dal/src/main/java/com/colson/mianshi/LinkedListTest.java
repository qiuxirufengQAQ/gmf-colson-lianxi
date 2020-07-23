package com.colson.mianshi;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {
    @Test
    public void testLinkedList(){
        // 初始化LinkedList,默认是first，last为Node节点，Node节点为内部类，存放三个Node，分别为当前item，prev和next
        List list = new LinkedList<>();
        List list2 = new LinkedList<>();
        list2.add("a");
        list2.add("b");
        // add方法会在last后添加一个Node节点
        list.add("1234");
        // addAll方法会在last节点后添加新的list的节点
        list.addAll(list2);
        // 会先找到这个Node的index，如果这个index是小于size一半的，就从前往后找到这个Node，然后删除这个Node，如果大于size一半，就从后往前找
        list.remove(2);
        // 会从前往后找和该元素一样的Node，然后删除掉这个Node
        list.remove("1234");

    }
}
