package com.colson.dal;

import java.math.BigDecimal;

public class MathTest {
    public static void main(String[] args) {
        //保留两位小数
        double d = 12.34568;
        double l = (double)Math.round(d * 100) / 100;
        System.out.println(l);
        //12.35
        //String的0.10的转化问题
        double a = 2.335;
        double b = 1.455;
        double ba = (a+b)/0;
        System.out.println(ba);
        String ab = String.valueOf(ba);
        System.out.println(ab);
        //3.79
        double c = 0.08978;
        System.out.println(c==0);
        System.out.println((double) Math.round(c*10000)/10000);

        //Bigdecimal的加减乘除
        BigDecimal decimal = new BigDecimal("12.12356");
        BigDecimal decimal1 = new BigDecimal("34.87562");
        //保留四位小数
        System.out.println(decimal.setScale(3,BigDecimal.ROUND_HALF_UP));
        //BigDecimal的加法
        System.out.println(decimal.add(decimal1));
        //BigDecimal的减法
        System.out.println(decimal1.subtract(decimal));
        //BigDecimal的除法
        System.out.println(decimal1.divide(decimal,6,BigDecimal.ROUND_HALF_UP));
        //BigDecimal的乘法
        System.out.println(decimal.multiply(decimal1));
    }
}
