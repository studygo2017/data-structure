package com.study.dataStructure.list.queue;

/**
 * 环形队列
 */
public class CircleQueue<E> {
    private final static int DEFAULT_SIZE = 10;

    //底层维护队列数据的数据
    private E[] arr;

    private int maxSize; //底层数组容量

    private int front; // 标记队列头索引

    private int rear;//标记队列尾索引

    public CircleQueue(int size){
        maxSize = size + 1; //环形队列要一位出来
        arr = (E[]) new Object[maxSize];
        //初始化标记队列头和队列尾索引的两个变量
        front = 0; //首元素下标
        rear = 0; //尾元素下标后一位
    }
    public CircleQueue(){
        this(DEFAULT_SIZE);
    }

    /**
     * 队列是否已满
     * front是首元素下标，rear是尾元素后一位;相邻时数组就已满，因为是环形的，可能front在rear前面，也可能在后面
     * @return
     */
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return front == rear;
    }

    /**
     * 入队
     * @return
     */
    public boolean add(E e){
        if( isFull() ){
            throw new RuntimeException("队列已满！");
        }
        arr[rear] = e;
        rear = (rear+1) % maxSize ;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public E remove(){
        if( isEmpty() ){
            throw new RuntimeException("队列为空！");
        }
        E e = arr[front];
        front = (front+1) % maxSize;
        return e;
    }

    /**
     * 查看队首元素
     * @return
     */
    public E peek(){
        if( isEmpty() ){
            throw new RuntimeException("队列为空！");
        }
        return arr[front];
    }

    /**
     * 返回队列长度
     * @return
     */
    public int size(){
        return (rear+maxSize-front) % maxSize;
    }

    /**
     * 展示队列所有元素
     */
    public void showQueue(){
        if( isEmpty() ){
            throw new RuntimeException("队列为空！");
        }
        for (int i = front; i!=rear ; i = (i+1)%maxSize ) {
            System.out.printf("arr[%d]=%d; \n",i,arr[i]);

        }
    }
}
