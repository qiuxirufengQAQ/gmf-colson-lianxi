package com.colson.tool;

import org.junit.Test;

public class TranslateHump {

    @Test
    public void translateToHump() {
        String str = "f_bidding_no_status";
        String s = str.substring(2);
        StringBuilder result = new StringBuilder();
        String[] s1 = s.split("_");
        for (int i = 0; i < s1.length; i++) {
            if (i == 0)
                result.append(s1[i]);
            else
                result.append(s1[i].substring(0, 1).toUpperCase()).append((s1[i].substring(1)));

        }
        System.out.println(result.toString());
    }

    @Test
    public void translateFromHump() {
        String str = "biddingNoStatus";
        StringBuilder result = new StringBuilder();
        result.append("f_");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)){
                result.append("_").append(Character.toLowerCase(ch));
            }else {
                result.append(ch);
            }
        }
        System.out.println(result.toString());
    }
}
