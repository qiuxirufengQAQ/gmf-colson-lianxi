package com.colson.lambda;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {
	public static void main(String[] args) {
		List<Apple> inventory = new ArrayList<>();
		inventory.add(new Apple("green",100));
		inventory.add(new Apple("blue",150));
		inventory.add(new Apple("red",180));

		List<Apple> greenApples =
				filter(inventory, (Apple a) -> "green".equals(a.getColor()));

		System.out.println(greenApples.get(0).getColor());
	}
	public static  List<Apple> filter(List<Apple> list, Predicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple e: list){
			if(p.test(e)){
				result.add(e);
			}
		}
		return result;
	}

	@Test
	public void testSorted(){
		List<String> list = new ArrayList<>();
		list.add("1234");
		list.add("2345");
		list.add("1001");
		list.add("9999");

		String collect = list.stream().sorted().collect(Collectors.joining(","));
		System.out.println(collect);

	}

	@Test
	public void testStringBuilder(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("123").append("456").append("789");
		System.out.println(stringBuilder.toString());
	}

	@Test
	public void getDateTime(){
		String sourceDateTime = "2019-06-15 00:00:00";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("YYYYMMdd");

		try {
			Date parse = sdf1.parse(sourceDateTime);
			System.out.println(sdf2.format(parse));
		} catch (ParseException e) {
			System.out.println("");;
		}
	}
}
