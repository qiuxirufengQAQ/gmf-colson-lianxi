package com.colson.gamble;


import org.junit.Test;

import java.util.Random;

public class kellyFormula {
	/**
	 *
	 * 凯利公式
	 */


	//判断这次是否输赢
	public static boolean isWinOrLose(double winRate){
		Random result = new Random();

		//赢
		if (winRate > result.nextDouble()){
			return true;
		}else {
			return false;
		}
	}

	//凯利公式计算本次投注的钱
	public static double payMoney(double principal,double winningRate,double odds){
		return principal*(winningRate*(odds+1)-1)/odds;
	}

	//计算赌10次会赢多少钱
	public static void main(String[] args) {
		double originalPrincipal = 10000;
		for (int i = 0; i < 100; i++) {
			Random randomRate = new Random();
			double winRate = randomRate.nextDouble();
			if (winRate<=0.5){
				winRate = 1-winRate;
			}
			double payMoney = payMoney(originalPrincipal,winRate,1);
			if (isWinOrLose(winRate)){
				originalPrincipal +=payMoney;
			}else {
				originalPrincipal -=payMoney;
			}
			System.out.println(i+":"+winRate+":"+payMoney+":"+originalPrincipal);
			if (originalPrincipal<=0){
				return;
			}
		}
	}


}
