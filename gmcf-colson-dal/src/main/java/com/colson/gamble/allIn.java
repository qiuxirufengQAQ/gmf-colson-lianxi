package com.colson.gamble;

import java.util.Random;

public class allIn {
	public static void main(String[] args) {
		double originalPrincipal = 10000;
		for (int i = 0; i < 10; i++) {
			Random winRate = new Random();
			Random result = new Random();
			if (result.nextDouble()<winRate.nextDouble()){
				originalPrincipal*=2;
				System.out.println(originalPrincipal);
			}else {
				originalPrincipal = 0;
				System.out.println(originalPrincipal);
				break;
			}
		}
	}
}
