package com.study.dataStructure.list.stack;

/**
 * 栈结构
 * 后进先出
 * 数组实现栈
 */
public class ArrayStack {

    //底层维护栈数据的数组
    private int[] arr;

    //栈顶元素索引
    private int topIndex = -1 ;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack(int size){
        arr = new int[size];
    }

    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    public boolean isFull(){
        return arr.length == topIndex+1;
    }

    public boolean isEmpty(){
        return topIndex <= -1 ;
    }

    public boolean push(int num){
        if( isFull() ){
            throw new RuntimeException("栈满");
        }
        arr[++topIndex] = num;
        return true;
    }

    public int pop(){
        if( isEmpty() ) throw new RuntimeException("栈已满");
        return arr[topIndex--];
    }

    public int size(){
        return topIndex+1;
    }

    public void list(){
        if(isEmpty()) System.out.println("栈为空");
        for (int i = topIndex; i >= 0 ; i--) {
            System.out.println(arr[i]);
        }
    }

}
