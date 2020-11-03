package com.colson.dataStructure;

public class ArrayQueue {
    /**
     * 数组数据
     */
    private int[] array;

    /**
     * 数组的最大容器
     */
    private int maxSize;

    /**
     * 数据长度
     */
    private int length = 0;

    /**
     * 数据的输出端标识
     */
    private int front = 0;

    /**
     * 数据的增加端标识位
     */
    private int rear = 0;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    /**
     * 添加数据的方法
     */
    public void add(int value) {
        if (length >= maxSize) {
            return;
        }
        array[rear] = value;

        rear = (rear + 1) % maxSize;
        length++;

    }

    /**
     * 获取数据
     */
    public int poll() {
        if (length <= 0) {
            return 0;
        }
        int result = array[front];
        array[front] = 0;
        front = (front + 1) % maxSize;
        length--;
        return result;
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        arrayQueue.add(1);
        arrayQueue.add(2);
        arrayQueue.add(4);
        arrayQueue.add(3);
        arrayQueue.add(5);
        arrayQueue.add(6);
        arrayQueue.add(3);
        arrayQueue.add(5);
        arrayQueue.add(5);
        arrayQueue.add(6);
        // 超过长度的数据将添加不进去
        arrayQueue.add(3);
        arrayQueue.add(5);
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        // 超过数组长度的数据将返回0
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());

    }
}
