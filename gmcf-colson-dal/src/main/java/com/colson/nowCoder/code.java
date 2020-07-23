package com.colson.nowCoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class code {
    public static void main(String[] args) {
        getSplitString();
    }

    /**
     * 首先输入数字n，表示要输入多少个字符串。连续输入字符串(输出次数为N,字符串长度小于100)。
     * 按长度为8拆分每个字符串后输出到新的字符串数组，长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     */
    public static void getSplitString(){
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()){
            int count = Integer.parseInt(reader.nextLine());
            int num = 0;
            String[] strings = new String[count];
            for(int i =0;i<count;i++){
                strings[i] = reader.nextLine();
            }

            for (String str:strings) {
                spiltString(str);
            }
        }
    }
    public static void spiltString(String str){
        if(str.length()>8){
            System.out.println(str.substring(0,8));
            spiltString(str.substring(8));
        }else{
            System.out.print(str);
            for (int i= 0;i<8-str.length();i++){
                System.out.print("0");
            }
            System.out.println();
        }
    }

    /**
     * 现在IPV4下用一个32位无符号整数来表示，一般用点分方式来显示，点将IP地址分成4个部分，每个部分为8位，表示成一个无符号整数（因此不需要用正号出现），如10.137.17.1，是我们非常熟悉的IP地址，一个IP地址串中没有空格出现（因为要表示成一个32数字）。
     * 现在需要你用程序来判断IP是否合法。
     */
    public static void getRightIPOrNot(){
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()){
            String str = reader.nextLine();
            if(str.contains("-")){
                System.out.println("NO");
            }
            String[] split = str.split("\\.");
            for (int i = 0; i < split.length; i++) {
                Integer num = Integer.parseInt(split[i]);
                if(num<0 || num>255){
                    System.out.println("NO");
                }
            }
            System.out.println("YES");

        }
    }

    /**
     * 请编写一个函数（允许增加子函数），计算n x m的棋盘格子（n为横向的格子数，m为竖向的格子数）
     * 沿着各自边缘线从左上角走到右下角，总共有多少种走法，要求不能走回头路，即：只能往右和往下走，不能往左和往上走。
     */
    public static void getSumPath() {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            String[] s = reader.nextLine().split(" ");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            System.out.println(getSumPathByMAndN(m, n));
        }
    }

    public static int getSumPathByMAndN(int m, int n) {
        if (m == 0 && n > 0) {
            return getSumPathByMAndN(0, n - 1);
        }
        if (m > 0 && n == 0) {
            return getSumPathByMAndN(m - 1, 0);
        }
        if (m == 0 && n == 0) {
            return 1;
        }
        return getSumPathByMAndN(m - 1, n) + getSumPathByMAndN(m, n - 1);
    }

    /**
     * 输入一个字符串。
     * 输出字符串中最长的数字字符串和它的长度。如果有相同长度的串，则要一块儿输出，但是长度还是一串的长度
     */
    public static void getMaxNumLength() {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            List<StringBuilder> maxSbList = new ArrayList<>();
            StringBuilder maxSb = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            char[] charArray = reader.nextLine().trim().toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] >= '0' && charArray[i] <= '9') {
                    sb.append(charArray[i]);
                } else {
                    sb = new StringBuilder();
                }

                if (sb.length() > maxSb.length()) {
                    maxSb = new StringBuilder();
                    maxSb.append(sb.toString());
                    maxSbList.clear();
                    maxSbList.add(sb);
                } else if (sb.length() == maxSb.length()) {
                    maxSbList.add(sb);
                }
            }
            for (StringBuilder sb1 : maxSbList) {
                System.out.print(sb1.toString());
            }
            if (maxSbList.size() == 0) {
                System.out.println("0");
            } else {
                System.out.println(maxSb.length());
            }
        }

    }

    public static int count(int amount, int[] array) {
        return count(amount, 0, array);
    }

    public static int count(int amount, int denomPointer, int[] array) {
        if (amount == 0) {
            return 1;
        } else if ((amount < 0) || (denomPointer >= array.length)) {
            return 0;
        } else {
            return count(amount - array[denomPointer], denomPointer, array) +
                    count(amount, ++denomPointer, array);
        }
    }

    /**
     * 编写一个函数，传入一个int型数组，返回该数组能否分成两组，
     * 使得两组中各元素加起来的和相等，并且，所有5的倍数必须在其中一个组中，
     * 所有3的倍数在另一个组中（不包括5的倍数），能满足以上条件，返回true；不满足时返回false
     */
    public static void getGroupBySum() {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            int count = Integer.parseInt(reader.nextLine());
            String[] stringNums = reader.nextLine().split(" ");
            int sum = 0;
            // 如果有5的倍数，则放到这个list中
            List<Integer> list1 = new ArrayList<>();
            // 如果有3的倍数，则放到这个list中
            List<Integer> list2 = new ArrayList<>();

            // 所有没分组的数据都放到这个list中
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                int num = Integer.parseInt(stringNums[i]);
                sum += num;
                if (num % 5 == 0) {
                    list1.add(num);
                    continue;
                }
                if (num % 3 == 0) {
                    list2.add(num);
                    continue;
                }
                list.add(num);
            }
            if (sum % 2 != 0) {
                System.out.println("false");
                return;
            }
            int totalValue = sum / 2;


            System.out.println("true");
            return;
        }
    }

    /**
     * 输入候选人的人数，第二行输入候选人的名字，第三行输入投票人的人数，第四行输入投票。
     */
    public static void getNumToVote() {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            Map<String, Integer> map = new HashMap<>();
            int count = Integer.parseInt(reader.nextLine());
            String[] members = reader.nextLine().split(" ");
            for (String member : members) {
                map.put(member, 0);
            }
            int invalidCount = 0;
            int votes = Integer.parseInt(reader.nextLine());
            String[] voteValues = reader.nextLine().split(" ");
            for (int i = 0; i < votes; i++) {
                if (map.containsKey(voteValues[i])) {
                    map.put(voteValues[i], map.get(voteValues[i]) + 1);
                } else {
                    invalidCount++;
                }
            }
            for (String member : members) {
                System.out.println(member + " : " + map.get(member));
            }
            System.out.println("Invalid : " + invalidCount);
        }
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
                    if (pointNum == 1) {
                        continue;
                    }
                    sb.append("零");
                    continue;
                }
                int i1 = Integer.parseInt(String.valueOf(arabNumMax[i]));
                if ("壹".equals(numArr[i1]) && "拾".equals(positionArr[pointNum - i - 1]) && i == 0) {
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
                        if (i == 1) {
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
