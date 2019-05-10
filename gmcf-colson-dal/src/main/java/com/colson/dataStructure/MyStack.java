package com.colson.dataStructure;

public class MyStack {
	private int[] arrays;

	private int maxSize;

	private int top;

	public MyStack(int size){
		arrays = new int[size];
		maxSize = size;
		top = -1;
	}


	//压入数据
	public void push(int num){
		if (top<maxSize-1){
			arrays[++top] = num;
		}
	}

	//弹出栈顶数据
	public int pop(){
		return arrays[top--];
	}

	//访问栈顶数据
	public int peek(){
		return arrays[top];
	}

	//判断栈是否为空
	public boolean isEmpty(){
		if (top>=0){
			return false;
		}
		return true;
	}


	public static void main(String[] args) {
		MyStack stack = new MyStack(3);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.peek());
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}
}
