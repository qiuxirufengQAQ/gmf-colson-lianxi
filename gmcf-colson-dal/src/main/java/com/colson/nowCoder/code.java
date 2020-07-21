package com.colson.nowCoder;

import java.util.Scanner;

public class code {
    public static void main(String[] args) {
        getChineseYuan();
    }

    /**
     * 考试题目和要点：
     * 1、中文大写金额数字前应标明“人民币”字样。中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整等字样填写。（30分）
     * 2、中文大写金额数字到“元”为止的，在“元”之后，应写“整字，如￥ 532.00应写成“人民币伍佰叁拾贰元整”。在”角“和”分“后面不写”整字。（30分）
     * 3、阿拉伯数字中间有“0”时，中文大写要写“零”字，阿拉伯数字中间连续有几个“0”时，中文大写金额中间只写一个“零”字，如￥6007.14，应写成“人民币陆仟零柒元壹角肆分“。
     */
    public static void getChineseYuan() {
        Scanner reader = new Scanner(System.in);
        String[] positionArr = new String[]{"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};
        String[] smallArr = new String[]{"角", "分"};
        String[] numArr = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        while (reader.hasNext()) {
            StringBuilder sb = new StringBuilder();
            String arabNum = reader.nextLine();
            sb.append("人民币");
            String[] arabNums = arabNum.split("\\.");
            int pointNum = arabNum.indexOf('.');
            char[] arabNumMax = arabNums[0].toCharArray();
            char[] arabNumMin = arabNums[1].toCharArray();
            for (int i = 0; i < pointNum; i++) {
                if (arabNumMax[i] == '0') {
                    if(pointNum == 1){
                        continue;
                    }
                    sb.append("零");
                    continue;
                }
                int i1 = Integer.parseInt(String.valueOf(arabNumMax[i]));
                if ("壹".equals(numArr[i1]) && "拾".equals(positionArr[pointNum - i - 1]) && i==0){
                    sb.append("拾");
                    continue;
                }
                sb.append(numArr[i1]);
                sb.append(positionArr[pointNum - i - 1]);
            }
            if (arabNumMin.toString().equals("00")) {
                sb.append("整");
            } else {
                for (int i = 0; i <= 1; i++) {
                    if (arabNumMin[i] == '0') {
                        if(i == 1 ){
                            continue;
                        }
//                        sb.append("零");
                        continue;
                    }
                    sb.append(numArr[Integer.parseInt(String.valueOf(arabNumMin[i]))]);
                    sb.append(smallArr[i]);
                }
            }
            String result = sb.toString();
            while (result.contains("零零")) {
                result = result.replace("零零", "零");
            }
            System.out.println(result);
        }
    }

    /**
     * 将一个字符中所有出现的数字前后加上符号“*”，其他字符保持不变
     */
    public static void addStarChar() {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            char[] str = reader.nextLine().toCharArray();
            StringBuilder sb = new StringBuilder();
            if (str[0] <= '9' && str[0] >= '0') {
                sb.append("*");
            }
            for (int i = 0; i < str.length - 1; i++) {
                if (str[i] >= '0' && str[i] <= '9' && (str[i + 1] < '0' || str[i + 1] > '9')) {
                    sb.append(str[i]);
                    sb.append('*');
                } else if ((str[i] < '0' || str[i] > '9') && (str[i + 1] >= '0' && str[i + 1] <= '9')) {
                    sb.append(str[i]);
                    sb.append('*');
                } else {
                    sb.append(str[i]);
                }
            }
            if (str[str.length - 1] <= '9' && str[str.length - 1] >= '0') {
                sb.append(str[str.length - 1]);
                sb.append("*");
            } else {
                sb.append(str[str.length - 1]);
            }
            System.out.println(sb.toString());
        }

    }

    /**
     * 首先输入要输入的整数个数n，然后输入n个整数。输出为n个整数中负数的个数，和所有正整数的平均值，结果保留一位小数
     */
    public static void getNegativeAndCount() {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            int num = Integer.parseInt(reader.nextLine());
            String[] nums = reader.nextLine().split(" ");
            int negativeCount = 0;
            int sum = 0;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                int numi = Integer.valueOf(nums[i]);
                if (numi < 0) {
                    negativeCount++;
                } else if (numi > 0) {
                    sum += numi;
                    count++;
                }
            }
            System.out.println(negativeCount + " " + Math.round(sum * 10.0 / count) / 10.0);
        }
    }


    /**
     * 自守数是指一个数的平方的尾数等于该数自身的自然数。例如：25^2 = 625，76^2 = 5776，9376^2 = 87909376。请求出n以内的自守数的个数
     */
    public static void getAutomorphicNumber() {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            int num = reader.nextInt();
            int count = 0;

            for (int i = num; i >= 0; i--) {
                int result = i * i;
                String iStr = String.valueOf(i);
                String resultStr = String.valueOf(result);
                if (resultStr.endsWith(iStr)) {
                    count++;
                    System.out.println(iStr + ":" + resultStr);
                }
            }
            System.out.println(count);
        }
    }

    /**
     * 功能:等差数列 2，5，8，11，14。。。。
     * 输入:正整数N >0
     * 输出:求等差数列前N项和
     * 返回:转换成功返回 0 ,非法输入与异常返回-1
     * 本题为多组输入，请使用while(cin>>)等形式读取数据
     */
    public static void getSumByEqualDifference() {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            int num = reader.nextInt();
            int sum = ((num * 3 - 1) + 2) * num / 2;
            System.out.println(sum);
        }

    }
}
