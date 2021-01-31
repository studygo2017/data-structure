package com.study.dataStructure.list.queue;

import com.study.dataStructure.list.link.single.tail.TailLinkedList;

/**
 * 带头结点和尾结点的单链表来实现队列结构
 * @param <E>
 */
public class LinkedListQueue<E> implements MyQueue<E> {

    private TailLinkedList<E> linkedList;

    public LinkedListQueue(){
        linkedList = new TailLinkedList<E>();
    }

    public int size() {
        return linkedList.size();
    }

    public E deQueue() {
        return linkedList.removeFirst();
    }

    public boolean enQueue(E e) {
        return linkedList.addLast(e);
    }

    public E getFront() {
        return linkedList.getFront();
    }

    public E getLast() {
        return linkedList.getLast();
    }
}
