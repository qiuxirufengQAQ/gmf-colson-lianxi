package com.colson.java8.stream;

import org.junit.Test;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamTest {

	@Test
	public void streamTestMethod(){
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");

		//allMatch
		//使用给定的 Predicate 检查 Stream 中的所有元素，全部都通过检测则返回 true，否则 false 。
		System.out.println(list.stream().allMatch(n->n.length()==1));//true
		System.out.println(list.stream().allMatch(n->n.equals("1")));//false

		//anyMatch
		//使用给定的 Predicate 检查 Stream 中的所有元素，至少有一个通过检测则返回 true，否则 false 。
		System.out.println(list.stream().anyMatch(n->n.equals("1")));//true
		System.out.println(list.stream().anyMatch(n->n.equals("1000")));//false

		//collect
		//collect 操作使用给定的 Collector 做 reduce 操作。

		//数组元素连接
		String collect = list.stream().collect(Collectors.joining(","));
		System.out.println(collect);//1,2,3

		//转成list
		List<String> collectList = list.stream().collect(Collectors.toList());
		System.out.println(collectList.toString());//[1, 2, 3]

		//根据城市分组
		List<Person> personList = new ArrayList<>();
		personList.add(new Person(10,"张三"));
		personList.add(new Person(10,"李四"));
		personList.add(new Person(12,"王五"));

		Map<Integer, List<Person>> ageListPersonMap = personList.stream().collect(Collectors.groupingBy(Person::getAge));
		List<Person> personList10 = ageListPersonMap.get(10);
		personList10.stream().forEach(n->System.out.print(n.getName()+" "));//张三 李四

		System.out.println();

		List<Person> personList11 = ageListPersonMap.get(11);
//		personList11.stream().forEach(n->System.out.print(n.getName()+" "));
		Assert.isTrue(personList11==null,"11岁的为空");

		List<Person> personList12 = ageListPersonMap.get(12);
		personList12.stream().forEach(n->System.out.print(n.getName()+" "));//王五

		System.out.println();

		//count
		//返回Stream中的元素总数
		System.out.println(list.stream().count());//3

		//distinct
		//返回唯一的元素列表，类似于 数据库 sql 中的 distinct 关键字。 比较时通过 equals 方法来判定是否相同。
		list.add("1");
		list.add("4");
		list.add("5");

		list.stream().distinct().forEach(n-> System.out.print(n+" "));//1 2 3 4 5

		System.out.println();

		//filter
		//使用给定的 Predicate 的筛选 Stream 元素，符合条件的留下并组成一个新的 Stream 。
		list.stream().filter(n->n.equals("5")).forEach(n-> System.out.print(n+" "));//5

		System.out.println();

		//findAny
		//返回任何一个不确定的元素，通过 Optional 来包装。如果在一个固定不变的组合中，返回第一个元素。
		System.out.println(list.stream().findAny().get());//1

		//findFist
		//返回第一个元素
		System.out.println(list.stream().findFirst().get());//1

		//flatMap
		//适用于如果Stream中的元素还是集合，能将集合中的元素组成一个平面的集合。简单来下面的例子，Stream 是二维的，因为 Stream 的元素还是数组，经过flag处理后，变成一维的了，所有元素位于一个Stream 下了。
		System.out.println(
				Stream.of(new Integer[]{1,2,3}, new Integer[]{4,5,6}, new Integer[]{7,8,9,0})
						.flatMap(a -> Arrays.stream(a))
						.map(n -> n + "").collect(Collectors.joining(",")));//1,2,3,4,5,6,7,8,9,0

		//forEach
		//逐个元素执行Consumer操作
		list.stream().forEach(n-> System.out.print(n+","));//1,2,3,1,4,5,

		System.out.println();

		//limit
		//取出指定个数的元素组成新的stream
		System.out.println(list.stream().limit(4).map(n->n+"").collect(Collectors.joining(",")));//1,2,3,1

		//map
		//map 方法的作用是依次对 Stream 中的元素进行指定的函数操作，并将按顺序将函数操作的返回值组合到一个新的 Stream 中.
		System.out.println(list.stream().map(n->n+"test").collect(Collectors.joining(",")));//1test,2test,3test,1test,4test,5test

		//max
		//max通过给定的比较器，将最大的元素取出来，放回Optional
		list.add("55");
		System.out.println(list.stream().max((a,b)->a.length()-b.length()).get());//55

		//min
		//min通过给定的比较器，将最小的元素取出来，返回Optional
		System.out.println(list.stream().min((a,b)->a.length()-b.length()).get());//1

		//noneMatch
		//noneMatch 于 allMatch, anyMatch 类似，使用给定的 Predicate 检查 Stream 中的所有元素，全部不通过检测则返回 true，否则 false 。
		System.out.println(list.stream().noneMatch(n->n.equals("98")));//true

		System.out.println(list.stream().noneMatch(n->n.equals("55")));//false

		//reduce
		//reduce的函数作为二元操作符，一个为前面操作的结果，一个作为当前元素，reduce会逐个对Stream中的元素执行指定的操作，并返回最终的结果
		System.out.println(list.stream().reduce((a,b)->a+b+",").get());//12,3,1,4,5,55,

		//skip
		//忽略给定个数的元素，返回剩下的元素组成Stream
		System.out.println(list.stream().skip(2).map(n -> n + "").collect(Collectors.joining(",")));//3,1,4,5,55

		//sorted
		//通过给定的比较器排序，将排序后的元素的Stream返回。

		list.add("2");
		list.add("1");
		System.out.println(list.stream().sorted().collect(Collectors.joining(",")));//1,1,1,2,2,3,4,5,55


		List<Person> numList = new ArrayList<>();
		Optional<Person> person = numList.stream().findFirst();
		System.out.println(person.get().getAge());


	}
}
