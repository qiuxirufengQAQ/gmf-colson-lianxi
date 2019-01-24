package com.colson.dal;

import org.apache.commons.lang3.StringUtils;

public class TwentyMonthTest {
    public static void main(String[] args) {

        StringBuilder twoYearAccountRepayStatus = new StringBuilder();

        String twoYearAccountRepayStatus1 = "";
        int num = 0;
        if (null != twoYearAccountRepayStatus1){
            num = 0;
        }
        twoYearAccountRepayStatus = twoYearAccountRepayStatus.append(twoYearAccountRepayStatus1.substring(0,num));
        if(StringUtils.isNotBlank(twoYearAccountRepayStatus.toString())&& twoYearAccountRepayStatus.toString().length()<24){
            int num1 = 24-twoYearAccountRepayStatus.toString().length();
            for (int i=0;i<num1;i++){
                twoYearAccountRepayStatus.insert(0,"/");
            }
        }
        System.out.println(twoYearAccountRepayStatus.toString());

    }
}
