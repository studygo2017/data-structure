package com.study.dataStructure.list.stack;

/**
 * 链表实现栈结构
 */
public class LinkedStack {

    private Node top = null;

    public boolean push(int value){
        Node node = new Node(value, null);
        if(top == null){
            top = node;
        }else{
            node.next = top;
            top = node;
        }
        return true;
    }

    public int pop(){
        if(top == null) throw new RuntimeException("栈为空");
        int data = top.data;
        top = top.next;
        return data;
    }

    public void printAll(){
        StringBuilder sb = new StringBuilder("Stack: [");
        if(top != null) {
            Node temp = top;
            while (true){
                if(temp == null) break;
                sb.append(temp.data+"  ");
                temp = temp.next;
            }
        }
        sb.append("]");
    }

    private static class Node{
        private int data;
        private Node next;

        public int getData(){
            return data;
        }
        public Node getNext(){
            return next;
        }

        public Node(){}
        public Node(int data,Node next){
            this.data = data;
            this.next = next;
        }

    }

}
