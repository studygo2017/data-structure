package com.study.dataStructure.list.link.single.homework;

import com.study.dataStructure.list.link.single.SingleLinkedList;
import com.study.dataStructure.list.link.single.TrueManNode;

/**
 * 1.求单链表中的有效结点个数 (略)
 * 2.查找单链表中的倒数第k个结点
 * 3.单链表的翻转
 * 4.从尾到头打印单链表
 * 5.合并两个有序的单链表，合并后的链表依然有序
 */
public class SglLinkedQuestion {

    /**
     * 查找单链表中的倒数第k个结点
     */
    public TrueManNode searchLastIndexOf(SingleLinkedList linkedList,int k){
        // 倒数第k个，即顺数的第(size-k)个
        int index = linkedList.size() - k;
        int size = linkedList.size();
        if( size==0 || index<=0 || index>=size ){
            return null;
        }
        TrueManNode temp = linkedList.getHead();
        for (int i = 0; i < (linkedList.size() - k); i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 翻转链表
     */
    public void reverseLink(SingleLinkedList linkedList){
        // TODO
    }


    /**
     * 从尾到头打印单链表
     */
    public void print(SingleLinkedList linkedList){
        // TODO
    }



}
