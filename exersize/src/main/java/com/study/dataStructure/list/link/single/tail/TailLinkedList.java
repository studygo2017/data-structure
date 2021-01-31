package com.study.dataStructure.list.link.single.tail;

/**
 * 带头结点和尾结点的单链表（用来实现队列）
 */
public class TailLinkedList<E> {

    //头结点
    private Node<E> head;

    //尾结点
    private Node<E> tail;

    private int size;

    public TailLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    private class Node<E>{
        E e;
        Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在尾结点增加
     * @return
     */
    public boolean addLast(E e){
        if( tail == null ){ //链表为空
            tail = new Node(e);
            head = tail;
        }else{ //链表不为空
            tail.next = new Node(e);
            tail = tail.next ;
        }
        size++;
        return true;
    }

    /**
     * 在头结点删除
     * @return
     */
    public E removeFirst(){
        if(head == null){ //链表为空
            throw new RuntimeException("没有元素");
        }
        Node<E> delNode = head;
        head = head.next;
        if(head == null) tail = null;
        size-- ;
        return delNode.e;
    }

    /**
     * 查看头结点元素
     * @return
     */
    public E getFront(){
        if(isEmpty()){ //链表为空
            throw new RuntimeException("没有元素");
        }
        return head.e ;
    }

    /**
     * 查看尾结点元素
     * @return
     */
    public E getLast(){
        if(isEmpty()){ //链表为空
            throw new RuntimeException("没有元素");
        }
        return tail.e;
    }

    public int size(){
        return size;
    }

}
