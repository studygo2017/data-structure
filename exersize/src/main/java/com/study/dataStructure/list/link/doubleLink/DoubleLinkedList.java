package com.study.dataStructure.list.link.doubleLink;

import com.study.dataStructure.list.link.single.TrueManNode;

/**
 * 双向链表
 * 与单链表比，
 */
public class DoubleLinkedList {

    //头结点
    private DoubleNode head = new DoubleNode();

    public boolean add(DoubleNode node){
        DoubleNode temp = head;
        while (true){
            if(temp.next == null){
                temp.next = node;
                temp = node.pre;
                break;
            }
            temp = temp.next;
        }
        return true;
    }

    public TrueManNode remove(int no){
        DoubleNode temp = head.next;
        if(temp == null) throw new RuntimeException("链表为空");
        boolean hasNo = false;
        while (true){
            if(temp.no == no){
                hasNo = true;
                break;
            }
            temp = temp.next;
        }
        if(hasNo){
            temp.pre.next = temp.next;
            if(temp.next!=null){
                temp.next.pre = temp.pre;
            }
        }else{
            throw new RuntimeException("元素不存在");
        }
       return null;
    }

    //改查都和单链表一样（当然双链表相比单链表有向前向后两个方向遍历的特性）

}
