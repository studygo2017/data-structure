package com.study.dataStructure.list.link;

import com.study.dataStructure.list.MyIterator;
import com.study.dataStructure.list.MyList;

import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E> {

    //虚拟头节点
    private Node dummyHead;

    private int size;

    public MyLinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public boolean addFirst(E e) {
        return add(0,e);
    }

    private class Node{
        private E e;
        private Node next;
        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }
    }

    public boolean add(E e) {
        //添加到链表头部效率最高 O(1)
//        dummyHead.next = new Node(e,dummyHead.next);
        add(0,e);
        return true;
    }

    public boolean add(int index, E e) {
        if(index < 0 || index > size ) throw new RuntimeException("索引越界");
        Node temp = dummyHead;
        for (int i = 0; i < size; i++) {
            if(index == i){
                temp.next = new Node(e,temp.next);
            }else {
                temp = temp.next;
            }
        }
        return true;
    }

    public E remove(int index) {
        if(index < 0 || index > size-1 ) throw new RuntimeException("索引越界");
        Node temp = dummyHead;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        Node delNode = temp.next;
        temp.next = delNode.next;
        return delNode.e;
    }

    public boolean set(int index, E e) {
        if(index < 0 || index > size-1 ) throw new RuntimeException("索引越界");
        Node temp = dummyHead;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        temp = new Node(e,temp.next);
        return true;
    }

    public E get(int index) {
        if(index < 0 || index > size-1 ) throw new RuntimeException("索引越界");
        Node temp = dummyHead;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.e;
    }

    public int size() {
        return size;
    }

    public void clear() {
        dummyHead.next = null;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contain(E e) {
        Node temp = dummyHead;
        while (temp.next != null){
            if(e.equals(temp.e)){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int indexOf(E e) {
        Node temp = dummyHead;
        int index = 0;
        while (temp.next != null){
            if(e.equals(temp.e)){
                return index;
            }
            temp = temp.next;
            index++;
        }
        return 0;
    }

    public MyIterator<E> iterator() {
        return new MyLinkedIterator<E>();
    }

    private class MyLinkedIterator<E> implements MyIterator<E> {
        int index = 0;

        public boolean hasNext() {
            return index < size;
        }

        public E next() {
            return (E) MyLinkedList.this.get(index++);
        }

        public E remove() {
            return (E) MyLinkedList.this.remove(index);
        }
    }
}
