package com.colson.lambda;

public class AppleHeavyWeightPredicate implements Predicate{
	public boolean test(Apple apple){
		return apple.getWeight() > 150;
	}
}
