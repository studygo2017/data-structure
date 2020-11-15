package com.study.dataStructure.queue;

public class OrdinaryQueue {
    //底层维护队列数据的数据
    private int[] arr;

    private int maxSize; //底层数组容量

    private int front; // 标记队列头索引

    private int rear;//标记队列尾索引

    public OrdinaryQueue(int size){
        maxSize = size;
        arr = new int[maxSize];
        //初始化标记队列头和队列尾索引的两个变量
        front = -1; //在首元素索引的前一位； 出队时该值就增加
        rear = -1; //尾元素索引; 入队时改值便增加
    }
    public OrdinaryQueue(){
        this(10); //默认容量为10
    }

    /**
     * 队列是否已满
     * @return
     */
    public boolean isFull(){
        return rear == maxSize-1;
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
     * @param num
     * @return
     */
    public boolean add(int num){
        if(isFull()){
            throw new RuntimeException("队列已满！");
        }
        arr[++rear] = num;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public int remove(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[++front];
    }

    /**
     * 观察队列首位元素
     * @return
     */
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }

    /**
     * 展示队列所有元素
     */
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
       int index = front+1;
        for (; index <= rear ; index++) {
            System.out.printf("arr[%d]=%d;\n",index,arr[index]);
        }
    }
}