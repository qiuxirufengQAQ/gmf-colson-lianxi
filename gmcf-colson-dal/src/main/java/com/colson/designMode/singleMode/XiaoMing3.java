package com.colson.designMode.singleMode;

public class XiaoMing3 {
	public Car3 goToSchool(){
		System.out.println("去学校");
		Car3 car3 = Car3.getCar3();
		car3.run();
		return car3;
	}

	public Car3 travel(){
		System.out.println("旅游");
		Car3 car3 = Car3.getCar3();
		car3.run();
		return car3;
	}

	public Car3 goToParty(){
		System.out.println("去聚会");
		Car3 car3 = Car3.getCar3();
		car3.run();
		return car3;
	}

	public static void main(String[] args) {

		//
		XiaoMing3 xiaoMing = new XiaoMing3();
		Car3 car1 = xiaoMing.goToSchool();
		Car3 car2 = xiaoMing.goToParty();
		Car3 car3 = xiaoMing.travel();
		System.out.println(car1.hashCode());
		//798154996
		System.out.println(car2.hashCode());
		//798154996
		System.out.println(car3.hashCode());
		//798154996
	}
}
