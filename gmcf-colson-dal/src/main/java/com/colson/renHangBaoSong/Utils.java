package com.colson.renHangBaoSong;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String getDicConvert(String type,String value){
//        性别
        Map<String,String> sexMap = new HashMap<>();
        sexMap.put("M","1");
        sexMap.put("F","2");
        sexMap.put("UKN","0");
        sexMap.put("FM","1");
        sexMap.put("MF","2");
        sexMap.put("O","9");
        sexMap.put("otherDefault","9");

//        最高学历
        Map<String,String> highestEductionMap = new HashMap<>();
        highestEductionMap.put("otherDefault","30");
        highestEductionMap.put("primary","80");
        highestEductionMap.put("middle","70");
        highestEductionMap.put("high","60");
        highestEductionMap.put("university","20");
        highestEductionMap.put("master","10");
        highestEductionMap.put("doctor","10");
        highestEductionMap.put("others","99");

//        行业
        Map<String,String> occupationMap = new HashMap<>();
        occupationMap.put("1","0");
        occupationMap.put("2","1");
        occupationMap.put("3","1");
        occupationMap.put("4","Y");
        occupationMap.put("5","3");
        occupationMap.put("6","Y");
        occupationMap.put("7","3");
        occupationMap.put("8","Y");
        occupationMap.put("9","5");
        occupationMap.put("Z","Z");

        if (type.equals("sex")){
            return sexMap.get(value);
        }else if (type.equals("highestEducation")){
            return highestEductionMap.get(value);
        }else {
            return occupationMap.get(value);
        }
    }
}
