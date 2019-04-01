package com.colson.competition;

import com.colson.leetCode.Solution;

import java.util.regex.Pattern;

public class Resolve {

	/**39个台阶
	 * 如果我每一步只能迈上1个或2个台阶。先迈左脚，然后左右交替，最后一步是迈右脚，也就是说一共要走偶数步。那么，上完39级台阶，有多少种不同的上法呢？
	 */
	static int sum = 0;
	public int thirtyNineSteps(){
		String result = "";
		int num = 39;
		stepsRecursion(result,num);
		return sum;
	}

	private void stepsRecursion(String result,int num){
		if (num<0){
			return;
		}
		if (num == 0){
			if (result.toString().length()%2==0){
				sum++;
			}
			return;
		}
		for (int i=1;i<=2;i++){
			stepsRecursion(result+i,num-i);
		}
	}
	public static void main(String[] args) {
		Resolve resolve = new Resolve();
		long startTime = System.currentTimeMillis();
		System.out.println(resolve.thirtyNineSteps());
		System.out.println(System.currentTimeMillis()-startTime);

		System.out.println(resolve.parttern("jfdsajflsdjalfjdaslfj1q0"));
	}

	private boolean parttern(String test){
		return Pattern.matches("^(.)*[0-5]$", test);
	}
}
