package com.colson.leetCode;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Solution3 {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x;}
	}

	//21 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null){
			return l2;
		}
		if (l2 == null){
			return l1;
		}
		ListNode head = new ListNode(1);
		ListNode temp = head;
		while (l1 !=null || l2 !=null){
			if (l1.val > l2.val){
				temp.next = new ListNode(l2.val);
				temp = temp.next;
				if (l2.next != null){
					l2 = l2.next;
				}else {
					temp.next = l1;
					break;
				}
			}else {
				temp.next = new ListNode(l1.val);
				temp = temp.next;
				if (l1.next != null){
					l1 = l1.next;
				}else {
					temp.next = l2;
					break;
				}
			}
		}
		return head.next;
	}

	//22括号生成  给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
	public static List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<>();
		helper(ans,"",0,0,n);
		return ans;
	}

	private static void helper(List<String> ans, String cur, int open, int close, int max){
		if (cur.length() == max*2){
			ans.add(cur);
			return;
		}
		if (open<max){
			helper(ans,cur+"(",open+1,close,max);

		}
		if (close<open){
			helper(ans,cur+')',open,close+1,max);
		}

	}

	//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0){
			return null;
		}
		ListNode head = new ListNode(Integer.MAX_VALUE);
		ListNode tempHead = head;
		ListNode temp = new ListNode(Integer.MAX_VALUE);
		int m=-1;
		while (lists.length!=0 ){
			int n=0;
			for (int i=0;i<lists.length;i++){
				if (i==0){
					temp = new ListNode(Integer.MAX_VALUE);
				}
				if (lists[i]==null){
					n++;
					continue;
				}
				if (temp.val>lists[i].val){
					temp = lists[i];
					m=i;
				}

			}
			if (n==lists.length){
				break;
			}
			tempHead.next = temp;
			tempHead.next.val = temp.val;
			tempHead = tempHead.next;
			if (m!=-1){
				lists[m] = lists[m].next;
			}

		}
		return head.next;
	}

	//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
	//你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
	//给定 1->2->3->4, 你应该返回 2->1->4->3.
	public static ListNode swapPairs(ListNode head) {
		if (head == null){
			return head;
		}
		if (head.next == null){
			return head;
		}
		ListNode l1 = head;
		ListNode l2 = head.next;
		head = l2;
		ListNode temp = head;
		int count =0;
		while (l2!=null){
			if (count!=0){
				temp.next = l2;
			}
			l1.next = l2.next;
			l2.next = l1;

			temp = l1;
			l1 = l1.next;
			if (l1 == null){
				break;
			}
			l2 = l1.next;
			count++;
		}
		return head;
	}

	//给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
	//k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
	public ListNode reverseKGroup(ListNode head, int k) {
		return null;
	}


	//给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	public static int removeDuplicates(int[] nums) {
		if (nums.length==0){
			return 0;
		}
		if (nums.length == 1){
			return 1;
		}
		int temp = 0;
		for (int i=0;i<nums.length-1;i++){
			while (nums[i] == nums[i+1]){
				if (i+1>=nums.length-1){
					break;
				}
				i++;
			}
			nums[temp++] = nums[i];
		}
		if (nums[nums.length-1]!=nums[nums.length-2]){
			nums[temp++] = nums[nums.length-1];
		}
		return temp;
	}

	//给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
	public static int removeElement(int[] nums, int val) {
		if(nums.length==0){
			return 0;
		}
		if (nums.length == 1){
			if (nums[0]==val){
				return 0;
			}else {
				return 1;
			}
		}
		int temp = 0;
		for (int i=0;i<nums.length;i++){
			while (nums[i]==val){
				if (i+1>=nums.length){
					break;
				}
				i++;
			}
			nums[temp] = nums[i];
			if (nums[i]!=val){
				temp++;
			}
		}
		return temp;
	}
	//1017给定正整数 N，返回小于等于 N 且具有至少 1 位重复数字的正整数。
	public static int numDupDigitsAtMostN(int N) {
		int count=0;
		Set set = new HashSet();
		for (int i=11;i<=N;i++){
			String[] split = String.valueOf(i).split("");
			for (int j=0;j<split.length;j++){
				set.add(split[j]);
			}
			if (set.size()!=split.length){
				count++;
			}
			set.clear();
		}
		return count;
	}

	public static void main(String[] args) {
		ListNode head1 = new ListNode(1);
		ListNode head2 = new ListNode(1);
		ListNode l1 = head1;
		ListNode l2 = head2;
		int[] arrays1 = new int[]{1,7,9};
		int[] arrays2 = new int[]{1,3,4};
		for (int i=1;i<arrays1.length;i++){
			l1.next = new ListNode(arrays1[i]);
			l1 = l1.next;
		}
		for (int j=1;j<arrays2.length;j++){
			l2.next = new ListNode(arrays2[j]);
			l2 = l2.next;
		}
		ListNode listNode = Solution3.mergeTwoLists(head1, head2);
		System.out.print(listNode.val);
		while (listNode.next!=null){
			listNode = listNode.next;
			System.out.print("->"+listNode.val);
		}

//		long startTime = System.currentTimeMillis();
//		List<String> list = Solution3.generateParenthesis(10);
//		System.out.println(Arrays.toString(list.toArray()));
//		System.out.println(list.size());
//		System.out.println(System.currentTimeMillis()-startTime);


		ListNode head3 = new ListNode(1);
		ListNode head4 = new ListNode(0);
//		ListNode head5 = new ListNode(2);
		ListNode l3 = head3;
		ListNode l4 = head4;
//		ListNode l5 = head5;
		int[] arrays3 = new int[]{1};
		int[] arrays4 = new int[]{0};
		int[] arrays5 = new int[]{2,6};
		for (int i=1;i<arrays3.length;i++){
			l3.next = new ListNode(arrays3[i]);
			l3 = l3.next;
		}
		for (int j=1;j<arrays4.length;j++){
			l4.next = new ListNode(arrays4[j]);
			l4 = l4.next;
		}
//		for (int k=1;k<arrays5.length;k++){
//			l5.next = new ListNode(arrays5[k]);
//			l5 = l5.next;
//		}
		ListNode[] lists = new ListNode[]{head3,head4};
		ListNode listNode1 = Solution3.mergeKLists(lists);
		System.out.println();
		while (listNode1!=null){
			System.out.print(listNode1.val+"->");
			listNode1 = listNode1.next;
		}

		ListNode head6 = new ListNode(1);
		ListNode l6 = head6;
		int[] arrays6 = new int[]{1,2,3,4,5};
		for (int i=1;i<arrays6.length;i++){
			l6.next = new ListNode(arrays6[i]);
			l6 = l6.next;
		}
		ListNode listNode2 = Solution3.swapPairs(head6);
		System.out.println();
		while (listNode2!=null){
			System.out.print(listNode2.val+"->");
			listNode2 = listNode2.next;
		}
		System.out.println();

		System.out.println(Solution3.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));

		System.out.println(Solution3.removeElement(new int[]{3,2,2,3},3));

//		long startTime = System.currentTimeMillis();
//		System.out.println(Solution3.numDupDigitsAtMostN(6718458));
//		System.out.println(System.currentTimeMillis()-startTime);

	}
}
