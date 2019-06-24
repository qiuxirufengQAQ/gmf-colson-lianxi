package com.colson.designMode.singleMode;

public class Car2 {
	//饿汉式单例模式

	//自己构造一个类
	public static Car2 car2 = new Car2();

	//私有化构造方法
	private Car2(){

	}

	//向整个系统提供这个实例
	public static Car2 getCar2(){
		return car2;
	}


	//调用方法
	public void run(){
		System.out.println("走。。。");
	}
}
