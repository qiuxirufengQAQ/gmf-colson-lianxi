package com.colson.nowCoder;

import org.junit.Test;

import java.util.Scanner;

public class TestNowCoder {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        int positiveCount = 0;
        int negativeCount = 0;
        double sum = 0;
        while (reader.hasNext()) {
            int num = reader.nextInt();
            if (num < 0) {
                negativeCount++;
            } else {
                positiveCount++;
                sum += num;
            }
            System.out.println(negativeCount);
            System.out.println(String.format("%.1f", positiveCount == 0 ? 0.0 : sum / positiveCount));
        }

    }

    public void spiltString(String str) {
        if (str.length() > 8) {
            System.out.println(str.substring(0, 8));
            spiltString(str.substring(8));
        } else {
            System.out.print(str);
            for (int i = 0; i < 8 - str.length(); i++) {
                System.out.print("0");
            }
            System.out.println();
        }
    }

    /*
    连续输入字符串(输出次数为N,字符串长度小于100)，请按长度为8拆分每个字符串后输出到新的字符串数组，
    长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
    首先输入一个整数，为要输入的字符串个数。
    例如：
    输入：2
    abc
    12345789
    输出：abc00000
    12345678
    90000000
     */
    @Test
    public void segmentation() {
        Scanner reader = new Scanner(System.in);

        int count = reader.nextInt();
        int num = 0;
        String[] strings = new String[count];
        while (reader.hasNext()) {
            strings[num++] = reader.next();
        }

        for (String str : strings) {
            spiltString(str.trim());
        }
    }

    /*
   Redraiment是走梅花桩的高手。Redraiment总是起点不限，从前到后，往高的桩子走，但走的步数最多，不知道为什么？你能替Redraiment研究他最多走的步数吗？
    样例输入
    6
    2 5 1 5 4 5
    样例输出
    3
    提示
    Example:
    6个点的高度各为 2 5 1 5 4 5
    如从第1格开始走,最多为3步, 2 4 5
    从第2格开始走,最多只有1步,5
    而从第3格开始走最多有3步,1 4 5
    从第5格开始走最多有2步,4 5
    所以这个结果是3。
     */
    @Test
    public void plumBlossomStake() {
        Scanner reader = new Scanner(System.in);

        int count = reader.nextInt();
        int num = 0;
        int max = 0;
        int[] intArr = new int[count];
        while (reader.hasNext()) {
            String string = reader.nextLine();
            String[] s = string.split(" ");
            for (int i = 0; i < s.length; i++) {
                intArr[i] = Integer.parseInt(s[i]);
            }
            int[] results = new int[count];
            for (int i = 0; i < intArr.length; i++) {
                results[i] = 1;
                for (int j = 0; j < i; j++) {
                    results[i] = Math.max(results[i], results[i] + 1);
                }
            }
            for (int result : results) {
                max = Math.max(result, max);
            }
            System.out.println(max);
        }
    }

    /**
     * 对字符中的
     * 各个英文字符（大小写分开统计），数字，空格进行统计，并按照统计个数由多到少输出,如果统计的个数相同，则按照ASII码由小到大排序输出 。如果有其他字符，则对这些字符不用进行统计。
     */
    public void charCount() {
        Scanner reader = new Scanner(System.in);
        String str = reader.nextLine().trim();
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            
        }
    }
}
