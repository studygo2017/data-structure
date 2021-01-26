package com.study.dataStructure.list.stack;

/**
 * 栈结构
 * 后进先出
 * 数组实现栈
 */
public class ArrayStack<T> {

    //底层维护栈数据的数组
    private T[] arr;

    //栈顶元素索引
    private int topIndex = -1 ;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack(int size){
        arr = (T[]) new Object[size];
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

    public boolean push(T t){
        if( isFull() ){
            throw new RuntimeException("栈满");
        }
        arr[++topIndex] = t;
        return true;
    }

    public T pop(){
        if( isEmpty() ) throw new RuntimeException("栈空");
        return arr[topIndex--];
    }
    public T peek(){
        if( isEmpty() ) throw new RuntimeException("栈空");
        return arr[topIndex];

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
