package com.colson.designMode.singleMode;

public class XiaoMing {
	public Car goToSchool(){
		System.out.println("去学校");
		Car car = new Car();
		car.run();
		return car;
	}



	public Car travel(){
		System.out.println("旅游");
		Car car = new Car();
		car.run();
		return car;
	}

	public Car goToParty(){
		System.out.println("去聚会");
		Car car = new Car();
		car.run();
		return car;
	}

	public static void main(String[] args) {
		XiaoMing xiaoMing = new XiaoMing();
		Car car1 = xiaoMing.goToSchool();
		Car car2 = xiaoMing.goToParty();
		Car car3 = xiaoMing.travel();
		System.out.println(car1.hashCode());
		//798154996
		System.out.println(car2.hashCode());
		//681842940
		System.out.println(car3.hashCode());
		//1392838282
	}
}
