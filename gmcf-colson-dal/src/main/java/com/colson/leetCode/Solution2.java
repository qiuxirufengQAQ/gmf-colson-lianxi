package com.colson.leetCode;

import org.apache.commons.lang3.StringUtils;

import static org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfvoType.INT_MIN;

public class Solution2 {
	//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
	//
	//比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
	//
	//L   C   I   R
	//E T O E S I I G
	//E   D   H   N
	//
	//之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
	public static String convert(String s, int numRows) {

		if (numRows==1){
			return s;
		}
		char[][] arrays = new char[numRows][s.length()];
		int j=0,k=0;
		boolean direction = true;
		//存数逻辑
		for (int i=0;i<s.length();i++){
			if (j == numRows-1){
				direction = false;
			}
			if (j == 0){
				direction = true;
			}
			arrays[j][k] = s.charAt(i);
			if (direction){
				j++;
			}else {
				j--;
				k++;
			}
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int a =0;a<numRows;a++){
			for (int b=0;b<s.length();b++){
				stringBuilder.append(arrays[a][b]);
			}
		}
		return stringBuilder.toString().replaceAll("\u0000","");
	}

	//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
	public static int reverse(int x) {
		if (x == 0){
			return 0;
		}
		int count = 0;
		boolean flag = true;
		if (x>=0){
			flag = true;
		}else {
			flag = false;
			x = Math.abs(x);
		}
		int[] array = new int[10];
		for (int i=0;i<10;i++){
			if (x == 0){
				break;
			}
			count++;
			array[i] = Math.abs(x % 10);
			x /= 10;
		}
		long result = 0;
		for (int i=0;i<count;i++){
			result = result*10 + array[i];
		}
		if ((flag && result>=(Math.pow(2,31)-1))||(!flag && result*(-1)<=(-Math.pow(2,31)))){
			return 0;
		}
		if (!flag){
			return (int)((-1)*result);
		}
		return (int)result;
	}

	public static void main(String[] args) {
		String s= "AB";
		System.out.println(Solution2.convert(s,1));


		System.out.println(Solution2.reverse(1563847412));
		System.out.println(Math.abs(-2147483648));
	}
}
