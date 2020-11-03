package com.colson.dataStructure;

public class SingleList {

    /**
     * 头节点
     */
    private Node head;

    /**
     * 链表的长度
     */

    /**
     * 获取头节点
     */
    public Node getHead() {
        return this.head;
    }

    /**
     * 添加数据
     */
    public void add(int key, String value) {
        if (head == null) {
            head = new Node(key, value);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(key, value);
        }
    }

    /**
     * 排序添加
     */
    public void sortAdd(int key, String value) {
        // 头结点为空,将数据放到头结点
        if (head == null) {
            head = new Node(key, value);
        } else {
            // 头节点不为空
            Node tempHead;
            Node tempTail;
            Node temp = head;
            Node newNode = new Node(key, value);
            if (head.getKey() > key) {
                newNode.next = head;
                head = newNode;
                return;
            }
            while (temp != null) {
                if (temp.getKey() == key) {
                    System.out.println("不能重复添加数据" + key);
                    break;
                }
                tempHead = temp;
                temp = temp.next;
                tempTail = temp;
                if (tempHead != null && tempTail == null) {
                    tempHead.next = newNode;
                    break;
                } else if (tempHead.getKey() < key && tempTail.getKey() > key) {
                    tempHead.next = newNode;
                    newNode.setNext(tempTail);
                    break;
                }

            }
        }

    }

    /**
     * 获取数据
     */
    public String get(int key) {
        Node temp = head;
        String result = null;
        while (temp != null) {
            if (temp.getKey() == key) {
                result = temp.getValue();
                break;
            }
            temp = temp.next;
        }
        return result == null ? "" : result;
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.getValue());
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SingleList list = new SingleList();
        list.sortAdd(2, "李逵");
        list.sortAdd(1, "宋江");
        list.sortAdd(2, "潘金莲");
        list.sortAdd(4, "鲁智深");
        list.sortAdd(3, "武松");

        list.print();
    }


    private class Node {
        private int key;
        private String value;
        private Node next;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
