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
    public static TrueManNode searchLastIndexOf(SingleLinkedList linkedList,int k){
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
     * 思路：
     *  创建一个新链表的表头，顺序遍历原链表，将原链表的每一个结点元素添加到新链表的最前面
     */
    public static void reverseLink(SingleLinkedList linkedList){
        if( linkedList.size()<=1 ) return ;
        TrueManNode reverseHead = new TrueManNode();

        TrueManNode temp = linkedList.getHead().next; //头结点
        while (true){
            //从头结点开始
            TrueManNode lastNext = reverseHead.next; //新链表的头结点
            reverseHead.next = temp; //放到最前面去
            reverseHead.next.next = lastNext;

            if(temp.next==null) break;
            temp = temp.next;
        }
        linkedList.getHead().next = reverseHead;
    }


    /**
     * 从尾到头打印单链表
     */
    public static void print(SingleLinkedList linkedList){
        // TODO 需要结合栈结构来实现

    }



}
