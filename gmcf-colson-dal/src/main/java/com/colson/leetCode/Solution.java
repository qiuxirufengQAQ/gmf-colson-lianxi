package com.colson.leetCode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Solution {

	//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
	//输入: [2,2,1]
	//输出: 1
	//输入: [4,1,2,1,2]
	//输出: 4
	public static int singleNumber(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<nums.length;i++){
			int count = 0;
			for (int j=0;j<nums.length;j++){
				if(nums[i]==nums[j]){
					count++;
				}
			}
			if (count!=2){
				return nums[i];
			}
		}
		return -1;
	}

	//给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
	//你可以假设数组是非空的，并且给定的数组总是存在众数。
	//输入: [3,2,3]
	//输出: 3
	//输入: [2,2,1,1,1,2,2]
	//输出: 2
	public static int majorityElement(int[] nums) {
		int n = nums.length;
		int count = 0;
		for(int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				if (nums[i] == nums[j]){
					count++;
				}
			}
			if (count>(n/2)){
				return nums[i];
			}
		}
		return -1;
	}

	//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
	//
	//    每行的元素从左到右升序排列。
	//    每列的元素从上到下升序排列。
	//[
	//  [1,   4,  7, 11, 15],
	//  [2,   5,  8, 12, 19],
	//  [3,   6,  9, 16, 22],
	//  [10, 13, 14, 17, 24],
	//  [18, 21, 23, 26, 30]
	//]
	//
	//给定 target = 5，返回 true。
	//
	//给定 target = 20，返回 false。
	public static boolean searchMatrix(int[][] matrix, int target) {
		return false;
	}



	public static class ListNode {
     	int val;
    	ListNode next;
    	ListNode(int x) { val = x;}
	}

	//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
	//
	//如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
	//
	//您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
	//
	//示例：
	//
	//输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
	//输出：7 -> 0 -> 8
	//原因：342 + 465 = 807
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Integer num1 = 0,num2 = 0,num3;
		Integer mul1 = 1;
		while(l1!=null){
			num1 = l1.val*mul1 + num1;
			mul1 = mul1*10;
			l1 = l1.next;
		}
		mul1 = 1;
		while(l2!=null){
			num2 = l2.val*mul1 + num2;
			mul1 = mul1*10;
			l2 = l2.next;
		}
		num3 = num1 + num2;
		ListNode p = new ListNode(num3%10);
		ListNode first = p;
		while(num3/10 != 0){
			num3 = num3/10;
			ListNode listNode = new ListNode(num3%10);
			p.next = listNode;
			p = listNode;
		}
		return first;
	}

	//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
	public static int lengthOfLongestSubstring(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		if (s.length() == 1){
			return 1;
		}
		if (s.length() == 0){
			return 0;
		}
		int result =0,num = 1;
		stringBuilder.append(s.substring(0,1));
		for (int i=1;i<s.length();i++){
			String c = s.substring(i,i+1);
			while (stringBuilder.toString().contains(c)){
				stringBuilder.delete(0,1);
				num--;
			}
			stringBuilder.append(c);
			num++;
			result = result>num?result:num;
		}
		return result;

	}
	//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
	//请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
	//你可以假设 nums1 和 nums2 不会同时为空。
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int[] nums3 = new int[nums1.length+nums2.length];
		int i=0,j=0,k = 0;
		for (;i<nums1.length && j<nums2.length;k++){
			if (j<nums2.length && nums1[i]>nums2[j]){
				nums3[k] = nums2[j];
				j++;
			}else {
				nums3[k] = nums1[i];
				i++;
			}
		}
		if (i>=nums1.length){
			for (;k<nums3.length && j<nums2.length;k++,j++){
				nums3[k] = nums2[j];
			}
		}else {
			for (;k<nums3.length && i<nums1.length;k++,i++){
				nums3[k] = nums1[i];
			}
		}
		int len = nums3.length;
		if (len%2==0){
			return (double)(nums3[len/2-1]+nums3[len/2])/2;
		}else {
			return (double)nums3[len/2];
		}
	}

	//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
	public static String longestPalindrome(String s) {
		if (s.length()<=1){
			return s;
		}
		String sub;
		for (int i=s.length();i>0;i--){//子串长度
			for (int j=0;j<=s.length()-i;j++){
				sub = s.substring(j,i+j);//子串
				int count =0;
				for (int k=0;k<sub.length()/2;k++){
					if (sub.charAt(k) == sub.charAt(sub.length()-k-1)){
						count++;
					}
					if (count == sub.length()/2){
						return sub;
					}
				}

			}
		}
		if (s.length() == 2){
			return s.substring(0,1);
		}
		return "";
	}


	public static void main(String[] args) {
		//singleNumber
		int[] nums = {2,2,1};
		System.out.println(Solution.singleNumber(nums));

		//majorityElement
		int[] nums2 = {2,2,1,1,1,2,2};
		System.out.println(Solution.majorityElement(nums2));

		//addTwoNumbers
		ListNode l1 = new ListNode(9);
		ListNode p1 = l1;

		ListNode l2 = new ListNode(1);
		ListNode p2 = l2;
		l2.next = new ListNode(9);
		l2 = l2.next;
		l2.next = new ListNode(9);
		l2 = l2.next;
		l2.next = new ListNode(9);
		l2 = l2.next;
		l2.next = new ListNode(9);l2 = l2.next;
		l2.next = (new ListNode(9));l2 = l2.next;
		l2.next = (new ListNode(9));l2 = l2.next;
		l2.next = (new ListNode(9));
		ListNode listNode = addTwoNumbers(p1,p2);
		System.out.print(listNode.val);
		while (listNode.next != null){
			listNode = listNode.next;
			System.out.print("->"+listNode.val);
		}
		System.out.println();
		//lengthOfLongestSubstring
		System.out.println(Solution.lengthOfLongestSubstring("pwwkew"));


		//findMedianSortedArrays

		System.out.println(findMedianSortedArrays(new int[]{1,3,5,7},new int[]{2,4,6,8}));

		System.out.println(Solution.longestPalindrome("ac"));
	}
}
