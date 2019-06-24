package com.colson.sourceCodeAnalysis;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntegerTest {

	@Test
	public void testEqualsMethod(){
		Integer i = 10;
		Integer j = 10;
		System.out.println(i == j);//true
		//

		Integer a = 128;
		Integer b = 128;
		System.out.println(a == b);//false

		int k = 10;
		System.out.println(k == i);//true
		int kk = 128;
		System.out.println(kk == a);//true

		Integer m = new Integer(10);
		Integer n = new Integer(10);
		System.out.println(m == n);//false
	}

	@Test
	public void testConstructionMethod(){
		Integer num1 = new Integer(13);

		System.out.println(num1);//13

		Integer num2 = new Integer("-12");

		System.out.println(num2);//12

	}

	@Test
	public void testToStringMethod(){
		Integer num1 = new Integer("123");

		System.out.println(num1.toString());//123

		System.out.println(Integer.toString(23));//23

		System.out.println(Integer.toString(56, 5));//211

		System.out.println(Integer.toString(56, 10));//56
	}

	@Test
	public void testAutoBinning(){
		Integer num1 = 23;//Integer num1 = Integer.valueOf(23);

		Integer num2 = 23;

		num2 *= 10;//num2 = Integer.valueOf(num2.intValue() * 10);

		System.out.println(num1);

		System.out.println(num2);

		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;

		Integer e = 321;
		Integer f = 321;

		Long g = 3L;//Long g = Long.valueOf(3L);
		Long h = 2L;//Long h = Long.valueOf(2L);

		System.out.println(c == d);//true
		System.out.println(e == f);//false
		System.out.println(c == (a + b));//true
		System.out.println(c.equals((a+b)));//true
		System.out.println(g == (a+b));//true
		System.out.println(g.equals(a+b));//false
		System.out.println(g.equals(a+h));//true
	}

	@Test
	public void testHashMap(){
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < 100; i++) {
			map.put(Integer.toString(i),Integer.toString(i));
		}
		Set<String> strings = map.keySet();
		strings.stream().forEach(n->System.out.print(n+" "));

		System.out.println();

		Collection<String> values = map.values();
		values.stream().forEach(n->System.out.print(n+" "));

	}

}
