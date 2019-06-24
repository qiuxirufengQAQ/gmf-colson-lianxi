package com.colson.designMode.singleMode;

public class XiaoMing2 {
	public Car2 goToSchool(){
		System.out.println("去学校");
		Car2 car2 = Car2.getCar2();
		car2.run();
		return car2;
	}

	public Car2 travel(){
		System.out.println("旅游");
		Car2 car2 = Car2.getCar2();
		car2.run();
		return car2;
	}

	public Car2 goToParty(){
		System.out.println("去聚会");
		Car2 car2 = Car2.getCar2();
		car2.run();
		return car2;
	}

	public static void main(String[] args) {

		//
		XiaoMing2 xiaoMing = new XiaoMing2();
		Car2 car1 = xiaoMing.goToSchool();
		Car2 car2 = xiaoMing.goToParty();
		Car2 car3 = xiaoMing.travel();
		System.out.println(car1.hashCode());
		//798154996
		System.out.println(car2.hashCode());
		//798154996
		System.out.println(car3.hashCode());
		//798154996
	}
}
