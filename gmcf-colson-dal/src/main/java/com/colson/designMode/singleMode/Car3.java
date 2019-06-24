package com.colson.designMode.singleMode;

public class Car3 {
	//懒汉式单例模式

	private static Car3 car3;

	private Car3(){

	}

	public static synchronized Car3 getCar3(){
		if (car3 == null){
			System.out.println("买车了。。。");
			car3 = new Car3();
		}
		return car3;
	}

	public void run(){
		System.out.println("走。。。");
	}
}
