package com.colson.dataStructure;

/**
 * 插入排序
 */
public class InsertSort {
	public static void main(String[] args) {
		int[] arrays = {4,2,8,9,5,7,6,1,3};
		for (int i = 1; i < arrays.length; i++) {
			insertArray(arrays,i);
		}
		printArray(arrays);
	}

	/**
	 * 插入数组中
	 * @param arrays
	 * @param num
	 */
	public static void insertArray(int[] arrays,int num){
		int temp = arrays[num];
		while (num-1 >=0 && temp < arrays[num-1]){
			arrays[num] = arrays[num-1];
			num --;
		}
		arrays[num] = temp;
	}

	/**
	 * 打印数组
	 */
	public static void printArray(int[] arrays){
		for (int i = 0; i < arrays.length; i++) {
			System.out.print(arrays[i]+" ");
		}
		System.out.println();
	}


}
