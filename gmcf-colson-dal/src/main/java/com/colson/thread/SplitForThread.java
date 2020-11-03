package com.colson.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SplitForThread {

    @Test
    public static List<List<ChuJu>> splitListForThread(List<ChuJu> list, int threadNum) {
        int circulation = list.size() / threadNum;//每个线程处理量
        int surplus = list.size() % threadNum;//取余
        int lastThreadNum = circulation < 1 ? surplus : threadNum;//最终执行线程数量

        // 建立对应线程的list
        List<List<ChuJu>> lists = new ArrayList<>(lastThreadNum);
        for (int i = 0; i < lastThreadNum; i++) {
            lists.add(new ArrayList<>());
        }
        // 先填充能整除以内的数
        int leftSize = list.size() - (list.size() % lastThreadNum);
        for (int i = 0; i < leftSize; i++) {
            int j = i / circulation;
            lists.get(j).add(list.get(i));
        }
        // 填充剩余的数据到最后一个list
        for (int i = leftSize; i < list.size(); i++) {
            lists.get(lastThreadNum - 1).add(list.get(i));
        }
        return lists;
    }

    public static void main(String[] args) {
        List<ChuJu> list = new ArrayList<>();
        for (int i = 0; i < 189; i++) {
            list.add(new ChuJu(UUID.randomUUID().toString()));
        }
        splitListForThread(list, 11);
    }
}
