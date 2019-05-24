package com.colson.dataStructure;


/**
 * 优先级队列
 */
public class PriorityQueue {
	private int maxSize;
	private int[] priQueArray;
	private int nItems;

	public PriorityQueue(int s){
		maxSize = s;
		priQueArray = new int[maxSize];
		nItems = 0;
	}

	//插入数据
	public void insert(int value){
		int j;
		if(nItems == 0){
			priQueArray[nItems++] = value;
		}else{
			j = nItems -1;
			//选择的排序方法是插入排序，按照从大到小的顺序排列，越小的越在队列的顶端
			while(j >=0 && value > priQueArray[j]){
				priQueArray[j+1] = priQueArray[j];
				j--;
			}
			priQueArray[j+1] = value;
			nItems++;
		}
	}

	//移除数据,由于是按照大小排序的，所以移除数据我们指针向下移动
	//被移除的地方由于是int类型的，不能设置为null，这里的做法是设置为 -1
	public int remove(){
		int k = nItems -1;
		int value = priQueArray[k];
		priQueArray[k] = -1;//-1表示这个位置的数据被移除了
		nItems--;
		return value;
	}

	//查看优先级最高的元素
	public int peekMin(){
		return priQueArray[nItems-1];
	}

	//判断是否为空
	public boolean isEmpty(){
		return (nItems == 0);
	}

	//判断是否满了
	public boolean isFull(){
		return (nItems == maxSize);
	}
}
