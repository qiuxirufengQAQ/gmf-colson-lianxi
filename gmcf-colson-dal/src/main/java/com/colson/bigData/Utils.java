package com.colson.bigData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Utils {


	//生成随机数字
	public static String genSimpleUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,17);
		return uuid;
	}

	public String genShortNo(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(0,8)+uuid.substring(9,13);
		return uuid;
	}

	public static String addOneMonth(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parse = sdf.parse(dateString);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(parse);
			rightNow.add(Calendar.MONTH,1);
			Date result = rightNow.getTime();
			return sdf.format(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String addThreeDays(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parse = sdf.parse(dateString);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(parse);
			rightNow.add(Calendar.DAY_OF_MONTH,3);
			Date result = rightNow.getTime();
			return sdf.format(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
