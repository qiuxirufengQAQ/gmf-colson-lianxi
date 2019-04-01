package com.colson.leetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//leetCode中11-20题
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

	//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数
	public static boolean isPalindrome(int x) {
		if(x<0){
			return false;
		}
		if(x/10 == 0){
			return true;
		}
		int count = 0,count2=0;
		int[] array = new int[10];
		while(x/10!=0){
			array[count] = x%10;
			x = x/10;
			count ++;
		}
		array[count] = x%10;
		for(int i=0;i<=count;i++){
			if(array[i] == array[count-i]){
				count2++;
			}
			if(count == count2){
				return true;
			}
		}
		return false;
	}


	//罗马数字转整数
	public static int romanToInt(String s) {
		char[] chars = s.toCharArray();
		int result = 0;
		int num = 0;
		for(int i=0;i<chars.length;i++){
			switch (chars[i]){
				case 'I':
					num = 1;
					break;
				case 'V':
					num = 5;
					break;
				case 'X':
					num = 10;
					break;
				case 'L':
					num = 50;
					break;
				case 'C':
					num = 100;
					break;
				case 'D':
					num = 500;
					break;
				case 'M':
					num = 1000;
					break;
			}
			result += num;
		}
		if (s.contains("IV") || s.contains("IX")){
			result -= 2;
		}
		if (s.contains("XL") || s.contains("XC")){
			result -= 20;
		}
		if (s.contains("CD") || s.contains("CM")){
			result -= 200;
		}
		return result;
	}

	/**
	 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。

	 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

	 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

	 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

	 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

	 在任何情况下，若函数不能进行有效的转换时，请返回 0。

	 说明：

	 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
	 */
	public static int myAtoi(String str) {
		str = str.trim();
		if ("".equals(str)){
			return 0;
		}
		char[] chars = str.toCharArray();
		char c0 = chars[0];
		boolean isNum = ('+' == c0 || '-' == c0 || (c0>='0' && c0<='9'));
		if (!isNum){
			return 0;
		}
		int start = (c0 == '+'||c0 =='-')?1:0;
		long result = 0;
		for (int i=start;i<chars.length;i++){
			int val = 0;
			if(chars[i]>='0' && chars[i]<='9'){
				val = Integer.parseInt(String.valueOf(chars[i]));
				val = c0 == '-'?-val:val;
				//溢出判断
				if(result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE/10 && val <-8)){
					return Integer.MIN_VALUE;
				}
				if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && val >=7)){
					return Integer.MAX_VALUE;
				}
			}else{
				break;
			}
			result = result*10 + val;
		}
		return (int) result;
	}

	/**
	 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	 说明：你不能倾斜容器，且 n 的值至少为 2。
	 */
	public static int maxArea(int[] height) {
		int result = 0;
		int sum = 0;
		for (int i=0;i<height.length;i++){
			for (int j=0;j<height.length;j++){
				if (i == j){
					break;
				}
				sum =Math.abs(i-j)*Math.min(height[i],height[j]);
				result = Math.max(result,sum);
			}
		}
		return result;
	}
	//整数转罗马数字
	public static String intToRoman(int num) {
		int consult = 0,remainder = 0;
		StringBuilder str = new StringBuilder();
		consult = num/1000;
		if (consult != 0){
			for (int i=0;i<consult;i++){
				str.append("M");
			}
			num = num%1000;
		}
		consult = num/500;
		if (consult != 0){
			str.append("D");
			num = num%500;
		}
		consult = num/100;
		if (consult != 0){
			for (int i=0;i<consult;i++){
				str.append("C");
			}
			num = num%100;
		}
		consult = num/50;
		if (consult != 0){
			str.append("L");
			num = num%50;
		}
		consult = num/10;
		if (consult != 0){
			for (int i=0;i<consult;i++){
				str.append("X");
			}
			num = num%10;
		}
		consult = num/5;
		if (consult != 0){
			str.append("V");
			num = num%5;
		}
		consult = num/1;
		if (consult != 0){
			for (int i=0;i<consult;i++){
				str.append("I");
			}
		}
		String resultStr = str.toString();
		return resultStr.replace("DCCCC","CM").replace("CCCC","CD").replace("LXXXX","XC").replace("XXXX","XL").replace("VIIII","IX").replace("IIII","IV");
	}

	//编写一个函数来查找字符串数组中的最长公共前缀。
	//如果不存在公共前缀，返回空字符串 ""。
	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0){
			return "";
		}
		if (strs.length == 1){
			return strs[0];
		}
		String chars = strs[0];
		String result = "";
		StringBuilder temp = new StringBuilder();
		int count,count2;
		for (int i=0;i<chars.length();i++){
			count = 0;
			temp = temp.append(chars.substring(i,i+1));
			for (int j=0;j<strs.length;j++) {
				if (strs[j].length()>=temp.length() && strs[j].charAt(i) == temp.toString().charAt(i)){
					count++;
				}
			}
			if (count == strs.length){
				result = temp.toString();
				continue;
			}
			break;
		}
		return result;
	}

	//给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
	//注意：答案中不可以包含重复的三元组。
	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);

		List<List<Integer>> listOut = new ArrayList<>();
		if (nums.length<3){
			return listOut;
		}
		for (int i=0;i<nums.length-2;i++){
			if(i == 0 || (i>0 && nums[i] != nums[i-1])){
				int j = i+1,k=nums.length-1;
				while (j<k){
					if (nums[j] + nums[k] + nums[i] == 0){
						listOut.add(Arrays.asList(nums[i],nums[j],nums[k]));
						while (j+1<nums.length-1 && nums[j] == nums[j+1])j++;
						while (k-1>j && nums[k] == nums[k-1])k--;
						j++;
						k--;
					}else if (nums[j] + nums[k] +nums[i] < 0){
						while (j+1<nums.length-1 && nums[j] == nums[j+1])j++;
						j++;
					}else {
						while (k-1>j && nums[k] == nums[k-1])k--;
						k--;
					}
				}
			}
		}
		return listOut;
	}

	//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
	//例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
	//与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		if (nums.length<3){
			return 0;
		}
		int result = 0,num1 = Integer.MAX_VALUE;
		for (int i=0;i<nums.length-2;i++){
			int j =i+1,k=nums.length-1;
			while (j<k){
				int num = Math.abs(target-nums[i]-nums[j]-nums[k]);
				if (num == 0){
					return target;
				}
				if (num1>num){
					num1 = num;
					result = nums[i]+nums[j]+nums[k];
				}
				if (nums[i]+nums[j]+nums[k]>target){
					k--;
				}else {
					j++;
				}
			}
		}
		return result;
	}

	//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
	//给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
	public static List<String> letterCombinations(String digits) {
		if ("".equals(digits)){
			return new ArrayList<>();
		}
		List<StringBuilder> list = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("a");
		list.add(stringBuilder);

		for (int i=0;i<digits.length();i++){
			int size = list.size();
			for (int j=0;j<size;j++){
				StringBuilder temp1 = new StringBuilder();
				StringBuilder temp2 = new StringBuilder();
				StringBuilder temp3 = new StringBuilder();
				temp1.append(list.get(j).toString());
				temp2.append(list.get(j).toString());
				temp3.append(list.get(j).toString());
				switch (digits.charAt(i)){
					//abc
					case '2':{
						list.get(j).append('a');
						temp1.append('b');
						temp2.append('c');
						break;
					}
					//def
					case '3':{
						list.get(j).append('d');
						temp1.append('e');
						temp2.append('f');
						break;
					}
					//ghi
					case '4':{
						list.get(j).append('g');
						temp1.append('h');
						temp2.append('i');
						break;
					}
					//jkl
					case '5':{
						list.get(j).append('j');
						temp1.append('k');
						temp2.append('l');
						break;
					}
					//mno
					case '6':{
						list.get(j).append('m');
						temp1.append('n');
						temp2.append('o');
						break;
					}
					//pqrs
					case '7':{
						list.get(j).append('p');
						temp1.append('q');
						temp2.append('r');
						temp3.append('s');
						break;
					}
					//tuv
					case '8':{
						list.get(j).append('t');
						temp1.append('u');
						temp2.append('v');
						break;
					}
					//wxyz
					case '9':{
						list.get(j).append('w');
						temp1.append('x');
						temp2.append('y');
						temp3.append('z');
						break;
					}
					default:
						return new ArrayList<>();
				}
				if (digits.charAt(i) == '7' || digits.charAt(i) == '9'){
					list.add(temp3);
				}
				list.add(temp1);
				list.add(temp2);
			}
		}
		List<String> result = new ArrayList<>();
		for (StringBuilder s:list){
			result.add(s.delete(0,1).toString());
		}
		return result;
	}

	//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
	//注意：
	//答案中不可以包含重复的四元组。
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);

		List<List<Integer>> listOut = new ArrayList<>();
		if (nums.length<4){
			return listOut;
		}
		if(nums.length==4 && nums[0]+nums[1]+nums[2]+nums[3]==target){
			listOut.add(Arrays.asList(nums[0],nums[1],nums[2],nums[3]));
			return listOut;
		}
		for (int i=0;i<nums.length-3;i++){
			if(i == 0 || (i>0 && nums[i] != nums[i-1])){
			for (int j=i+1;j<nums.length-2;j++){
				if (j == i+1 || (j>i+1 && nums[j] != nums[j-1])){
					int k = j+1,m=nums.length-1;
					while (k<m){
						if (nums[j] + nums[k] + nums[i] +nums[m] == target){
							listOut.add(Arrays.asList(nums[i],nums[j],nums[k],nums[m]));
							while (k+1<nums.length-1 && nums[k] == nums[k+1])k++;
							while (m-1>j && nums[m] == nums[m-1])m--;
							k++;
							m--;
						}else if (nums[j] + nums[k] +nums[i] +nums[m] < target){
							while (k+1<nums.length-1 && nums[k] == nums[k+1])k++;
							k++;
						}else {
							while (m-1>j && nums[m] == nums[m-1])m--;
							m--;
						}
					}
				}
				}
			}
		}
		return listOut;
	}

	//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	//有效字符串需满足：
	//    左括号必须用相同类型的右括号闭合。
	//    左括号必须以正确的顺序闭合。
	//注意空字符串可被认为是有效字符串。
	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i=0;i<s.length();i++){
			switch (s.charAt(i)){
				case '(':
					stack.push('(');
					break;
				case ')':
					if (stack.size() != 0 && stack.lastElement() == '('){
						stack.pop();
						break;
					}else {
						return false;
					}
				case '{':
					stack.push('{');
					break;
				case '}':
					if (stack.size() != 0 && stack.lastElement() == '{'){
						stack.pop();
						break;
					}else {
						return false;
					}
				case '[':
					stack.push('[');
					break;
				case ']':
					if(stack.size() != 0 && stack.lastElement() == '['){
						stack.pop();
						break;
					}else {
						return false;
					}
					default:return false;
			}
		}
		if (stack.empty()){
			return true;
		}
		return false;
	}



	public static void main(String[] args) {
		String s= "AB";
		System.out.println(Solution2.convert(s,1));


		System.out.println(Solution2.reverse(1563847412));
		System.out.println(Math.abs(-2147483648));

		System.out.println(Solution2.isPalindrome(10022201));

		System.out.println(Solution2.romanToInt("MCMXCIV"));

		System.out.println(Solution2.myAtoi("2147483648"));

		System.out.println(Solution2.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));

		System.out.println(Solution2.intToRoman(9));

		System.out.println(Solution2.longestCommonPrefix(new String[]{"aa","a"}));

		long startTime = System.currentTimeMillis();
		List<List<Integer>> lists = Solution2.threeSum(new int[]{0,0,0});
		System.out.println(System.currentTimeMillis()-startTime);
		System.out.println(lists.size());
		for (List<Integer> list:lists){
			for (Integer integer:list){
				System.out.print(integer+" ");
			}
			System.out.println();
		}


		System.out.println(Solution2.threeSumClosest(new int[]{-1,2,1,-4},-1));


		System.out.println(Solution2.letterCombinations(""));

		List<List<Integer>> lists1 = Solution2.fourSum(new int[]{0,-1,0,1,-2,-5,3,5,0}, 6);
		for (List<Integer> list:lists1){
			System.out.println(list.toString());
		}
		System.out.println();

		System.out.println(Solution2.isValid("]"));
	}
}
