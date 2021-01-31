package com.study.dataStructure.list.queue;

public interface MyQueue<E> {

    int size();

    //出队
    E deQueue();

    //入队
    boolean enQueue(E e);

    //查看队首元素
    E getFront();

    //查看队尾元素
    E getLast();
}
