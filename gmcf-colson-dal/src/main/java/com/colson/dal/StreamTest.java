package com.colson.dal;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        //使用Arrays.asList方法
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println(strings.toString());
        //使用流方法
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered.toString());

        //生成是个随机数字
        //limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println(squaresList.toString());

        //filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：

        List<String> strings1 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        int count = (int)strings1.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        //sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
        random.ints().limit(10).sorted().forEach(System.out::println);

        //parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
        int count1 = (int)strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count1);
        //流并行输出
        strings.parallelStream().filter(string -> !string.isEmpty()).forEach(string->System.out.println(string));

        //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        List<String> filtere = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        //可以进行add操作
        filtere.add("123");
        System.out.println(filtere.toString());

        //toSet操作
        Set filtSet = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toSet());
        filtSet.add("456");
        filtSet.add("jkl");
        System.out.println(filtSet.toString());

        //合并元素
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).limit(3).collect(Collectors.joining(", "));
        System.out.println(mergedString);

        //另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。

        List<Integer> numbers1 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers1.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("个数 : " + stats.getCount());

        //Integer a = 10,默认会执行Integer a = Integer.valueOf(10)的操作
        Integer a = 10;
        Integer b = Integer.valueOf(10);
        if (a==b){
            System.out.println("1");
        }

        StreamTest streamTest = new StreamTest();
        streamTest.setName("wang");
        System.out.println(streamTest.equals(streamTest));
        int ee = 10;
        System.out.println("124");
        System.out.println("-------YouMeek-------ee值-" + ee + "," + "当前类-" + "StreamTest" + "," + "当前方法-" + "main");
        System.out.println("-------YouMeek-------ee值-" + ee + "," + "当前类-" + "StreamTest" + "," + "当前方法-" + "main");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.toString().equals(this.toString())){
            return true;
        }

        return false;
    }
    public void testTem(String num){
        int i=10;
    }
}
