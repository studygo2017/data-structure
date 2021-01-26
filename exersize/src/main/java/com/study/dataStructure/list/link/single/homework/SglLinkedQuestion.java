package com.study.dataStructure.list.link.single.homework;

import com.study.dataStructure.list.link.single.SingleLinkedList;
import com.study.dataStructure.list.link.single.TrueManNode;
import com.study.dataStructure.list.stack.ArrayStack;

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
     *  创建一个新链表的表头，顺序遍历原链表，将原链表的每一个结点元素添加到新表头的最前面
     */
    public static void reverseLink(SingleLinkedList linkedList){
        if( linkedList.size()<=1 ) return ;

        TrueManNode newHead = new TrueManNode();
        TrueManNode current = linkedList.getHead().next; //首元素开始，用于遍历原链表的
        TrueManNode next = null;
        while(current!=null){ //尾元素进不来循环
            next = current.next; //当前遍历的下一位
            current.next = newHead.next; //原来的第一位地址值，由新的第一位指向，变成第二位
            newHead.next = current;

            current = next; //赋一个新的地址值
        }
        linkedList.getHead().next = newHead.next;
    }


    /**
     * 从尾到头打印单链表
     */
    public static void print(SingleLinkedList linkedList){
        // 需要结合栈结构来实现
        ArrayStack<TrueManNode> stack = new ArrayStack<TrueManNode>();
        TrueManNode node = linkedList.getHead().next; //链表的第一个元素
        while(true){
            if(node == null) break;
            stack.push(node);
            node = node.next;
        }
        while (true){
            if( !stack.isEmpty() ) break;
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个有序单链表，要求合并后的结果依然有序
     */
    public SingleLinkedList mergeSingleLink(SingleLinkedList link,SingleLinkedList link2){


        return null;
    }



}
