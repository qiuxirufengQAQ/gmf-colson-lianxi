package com.colson.dataStructure;


/**
 * 队列
 */
public class MyQueue {
	private Object[] queArray;
	//队列总大小
	private int maxSize;
	//前端
	private int front;
	//后端
	private int rear;
	//队列中元素的实际数目
	private int nItems;

	public MyQueue(int s){
		maxSize = s;
		queArray = new Object[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	//队列中新增数据
	public void insert(int value){
		if(isFull()){
			System.out.println("队列已满！！！");
		}else{
			//如果队列尾部指向顶了，那么循环回来，执行队列的第一个元素
			if(rear == maxSize -1){
				rear = -1;
			}
			//队尾指针加1，然后在队尾指针处插入新的数据
			queArray[++rear] = value;
			nItems++;
		}
	}

	//移除数据
	public Object remove(){
		Object removeValue = null ;
		if(!isEmpty()){
			removeValue = queArray[front];
			queArray[front] = null;
			front++;
			if(front == maxSize){
				front = 0;
			}
			nItems--;
			return removeValue;
		}
		return removeValue;
	}

	//查看队头数据
	public Object peekFront(){
		return queArray[front];
	}


	//判断队列是否满了
	public boolean isFull(){
		return (nItems == maxSize);
	}

	//判断队列是否为空
	public boolean isEmpty(){
		return (nItems ==0);
	}

	//返回队列的大小
	public int getSize(){
		return nItems;
	}


	public static void main(String[] args) {
		MyQueue queue = new MyQueue(3);
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);//queArray数组数据为[1,2,3]

		System.out.println(queue.peekFront()); //1
		queue.remove();//queArray数组数据为[null,2,3]
		System.out.println(queue.peekFront()); //2

		queue.insert(4);//queArray数组数据为[4,2,3]
		queue.insert(5);//队列已满,queArray数组数据为[4,2,3]

	}
}
