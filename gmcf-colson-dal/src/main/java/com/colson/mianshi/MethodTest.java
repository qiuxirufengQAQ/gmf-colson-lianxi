package com.colson.mianshi;

import com.colson.pojo.Parent;

import java.util.ArrayList;
import java.util.List;

public class MethodTest {

	<T extends Number> List<T> getT(T t){
		List<T> list = new ArrayList<>();
		list.add(t);
		return list;
	}
	public static void main(String[] args) {
		Son s = new Son();
//		System.out.println(p.age);
//
//		System.out.println(p.getAge());
		System.out.println(s.name);
		MethodTest methodTest = new MethodTest();
		List<Integer> t = methodTest.getT(new Integer(12));
		System.out.println(t.get(0));




	}
}
