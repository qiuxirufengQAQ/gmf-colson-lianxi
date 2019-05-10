package com.colson.dataStructure;

/**
 * 选择排序
 */
public class ChooseSort {

	/**
	 * 交换方法
	 */
	public static void swap(int[] arrays,int a,int b){
		int c = arrays[a];
		arrays[a] = arrays[b];
		arrays[b] = c;
	}

	/**
	 * 查询最小的坐标
	 */
	public static int findMin(int[] arrays,int start){
		int minIndex = start;
		for (int i = start; i < arrays.length; i++) {
			if (arrays[minIndex] > arrays[i]){
				minIndex = i;
			}
		}
		return minIndex;
	}

	/**
	 * 打印数组
	 * @param arrays
	 */
	public static void printArray(int[] arrays){
		for (int i = 0; i < arrays.length; i++) {
			System.out.print(arrays[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arrays = {4,2,8,9,5,7,6,1,3};
		for (int i = 0; i < arrays.length - 1; i++) {
			int min = ChooseSort.findMin(arrays, i);
			ChooseSort.swap(arrays,i,min);
		}
		ChooseSort.printArray(arrays);
	}
}
