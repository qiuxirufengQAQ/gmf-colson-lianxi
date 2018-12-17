package com.colson.dal;

import java.util.regex.Pattern;

public class NumToIncreaseNoTest {
    public static void main(String[] args) {
        String numAndString = "N*111111111111111111111***1111111111111111111**11NN1111NN11111111111";
        System.out.println(numAndString.length());
        String s = numToIncreaseNo(numAndString);
        System.out.println(s.length());
        System.out.println(s);
    }

    /**
     * 实现将1111的数字转化为1234，如果大于7的话，用7来补
     * @param a
     * @return
     */
    public static String numToIncreaseNo(String a){
        char[] chars = a.toCharArray();
        for (int i=1;i<chars.length;i++){
            //(char)48表示为0
            //(char)57表示为9
            if (chars[i]==49 && chars[i-1]>48 && chars[i-1]<56){
                if (chars[i-1]==55){
                    chars[i] = (char)(int)chars[i-1];
                }else{
                    chars[i] = (char)((int)chars[i-1]+1);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c:chars){
            if (c>48 && c<57){
                stringBuilder.append(c);
            }else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
