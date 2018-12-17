package com.colson.dal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringTest {
    public static void main(String[] args) throws Exception{
        //日期转化为String
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(dateFormat.format(date));
        //String转化为Date
        String dateString = "20160829120000";
        System.out.println(dateFormat.parse(dateString).getClass().getName());
    }
}
