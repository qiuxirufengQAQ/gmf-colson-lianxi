package com.colson.dal;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilsTest {
    public static void main(String[] args) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //根据秒来截取
        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.SECOND)));
        //2018-12-06 11:55:55
        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.ERA)));
        //0001-01-01 00:00:00
        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.YEAR)));
        //2018-01-01 00:00:00
        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.MONTH)));
        //2018-12-01 00:00:00
//        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.WEEK_OF_YEAR)));
        //IllegalArgumentException
//        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.WEEK_OF_MONTH)));
        //IllegalArgumentException
        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.DATE)));
        //2018-12-06 00:00:00
        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.DAY_OF_MONTH)));
        //2018-12-06 00:00:00
//        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.DAY_OF_YEAR)));
        //IllegalArgumentException: The field 6 is not supported
//        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.DAY_OF_WEEK)));
        //IllegalArgumentException: The field 7 is not supported
//        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.DAY_OF_WEEK_IN_MONTH)));
        //IllegalArgumentException: The field 8 is not supported
        System.out.println(dateFormat.format(DateUtils.truncate(date, Calendar.DAY_OF_MONTH)));


        //DateUtils.truncate测试
        System.out.println(DateUtils.truncate(date,Calendar.DAY_OF_MONTH));
        //Sat Jan 19 00:00:00 CST 2019
        System.out.println(DateUtils.truncate(date,Calendar.HOUR_OF_DAY));
        //Sat Jan 19 12:00:00 CST 2019
        System.out.println(DateUtils.truncate(date,Calendar.MINUTE));
        //Sat Jan 19 12:25:00 CST 2019
        System.out.println(DateUtils.truncate(date,Calendar.SECOND));
        //Sat Jan 19 12:27:57 CST 2019
        System.out.println(DateUtils.truncate(date,Calendar.MONTH));
        //Tue Jan 01 00:00:00 CST 2019
        System.out.println(DateUtils.truncate(date,Calendar.YEAR));
        //Tue Jan 01 00:00:00 CST 2019
    }

}
