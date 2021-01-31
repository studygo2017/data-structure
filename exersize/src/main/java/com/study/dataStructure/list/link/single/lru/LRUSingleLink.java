package com.study.dataStructure.list.link.single.lru;

import com.study.dataStructure.list.link.MyLinkedList;
import com.study.dataStructure.list.link.single.SingleLinkedList;
import com.study.dataStructure.list.link.single.tail.TailLinkedList;

import javax.swing.text.TabableView;

/**
 * 用单链表来实现一个玩具LRU缓存
 * LRU: 缓存淘汰策略的一种——最近最少使用策略（Least Recently Used）
 * 实现思路：
 *  1.在一个单链表，设置默认的缓存大小，如100个元素；
 *  2.逻辑中从DB查询的元素，若在缓存中存在时，则移动到缓存最前面；
 *  3.如缓存中没有，放入缓存，若缓存已达容量上限，则“淘汰”（删除）尾部的元素——越是靠前的元素，才越是最近使用过的
 */
public class LRUSingleLink<E> {

    private MyLinkedList<E> linkedList ;

    private int capacity ;

    public LRUSingleLink(int capacity){
        this.linkedList = new MyLinkedList();
        this.capacity = capacity;
    }

    public LRUSingleLink(){
        this(10);
    }

    /**
     * 存储到缓存
     */
    public boolean save(E e){
        if( isFull() ){ //如果存储空间已满，就要从尾结点出删除
            linkedList.removeLast();
        }
        //添加到头结点
        return linkedList.addFirst(e);
    }

    /**
     * 当查询缓存中已存在的元素时，移到链表表头
     */
    public boolean moveToFirst(E e){
        int i = linkedList.indexOf(e);
        if( i == -1) return false;
        if(i > 0){
            linkedList.addFirst(linkedList.remove(i));
        }
        return true;
    }

    public boolean isFull(){
        return linkedList.size() >= capacity;
    }

}
