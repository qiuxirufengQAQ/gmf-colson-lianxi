package com.colson.dataStructure;


/**
 * 冒泡排序
 * 	①、比较相邻的元素。如果第一个比第二个大，就交换他们两个。

 　　②、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数（也就是第一波冒泡完成）。

 　　③、针对所有的元素重复以上的步骤，除了最后一个。

 　　④、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class BubbleSort {


	/**
	 * 交换位置方法
	 * @param arrays
	 * @param a
	 * @param b
	 */
	public static void swap(int[] arrays,int a,int b){
		int c = arrays[a];
		arrays[a] = arrays[b];
		arrays[b] = c;
	}

	public static void printArray(int[] arrays){
		for (int i = 0; i < arrays.length; i++) {
			System.out.print(arrays[i]+" ");
		}
		System.out.println();
	}


	public static void main(String[] args) {
		int [] arrays = {4,2,8,9,5,7,6,1,3};

		for (int i = arrays.length-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arrays[j]>arrays[j+1]){
					BubbleSort.swap(arrays,j,j+1);
				}
			}
		}
		BubbleSort.printArray(arrays);
	}
}
