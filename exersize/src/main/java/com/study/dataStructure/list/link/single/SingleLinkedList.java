package com.study.dataStructure.list.link.single;

import javax.management.relation.RoleUnresolved;

/**
 * 基础单链表
 */
public class SingleLinkedList{

    /**
     * 头结点
     */
    private TrueManNode head = new TrueManNode();

    public TrueManNode getHead(){
        return head;
    }

    /**
     * 添加结点
     * @return
     */
    public boolean add(TrueManNode node){
        if(isEmpty()) {
            head.next = node;
        }else{
            TrueManNode temp = head.next;
            while(true){
                if(temp.next == null){ //没有找到，就往后继结点遍历
                    temp.next = node;
                    break;
                }
                temp = temp.next;
            }
        }
        return true;
    }

    /**
     * 删除结点
     * @return
     */
    public void remove(TrueManNode node){
        TrueManNode temp = head; //指向头结点
        while(temp.next!=null){
            if(temp.next.no == node.no){
                temp.next = temp.next.next; //没有指针指向这个结点，自然便会被gc
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("没有这个结点");
    }

    /**
     * 改结点
     * @return
     */
    public boolean updateNode(TrueManNode node){
        if( isEmpty() ) throw new RuntimeException("链表为空");
        TrueManNode temp = head;
        while(temp.next != null){
            if(temp.no == node.no){
                temp = node;
                return true;
            }
        }
        throw new RuntimeException("没有编号"+node.no+"的结点");
    }

    /**
     *
     * @return
     */
    public String toString(){
        if( isEmpty() ) return "[]";
        StringBuilder sb = new StringBuilder("[");
        TrueManNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            sb.append(temp.toString());
            if(temp.next!=null) sb.append("   ");
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty(){
        return head == null || head.next == null;
    }

    /**
     * 获取链表的有效结点个数
     * @return 链表长度
     */
    public int size(){
        TrueManNode temp = head.next; //首结点
        if(temp == null){
            return 0;
        }
        int size = 1;
        while (temp.next != null){
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 翻转当前链表（复习一遍）
     * @return
     */
    public SingleLinkedList reverse(){
        if(size()<=1) return this;
        //当前表头 head
        TrueManNode newHead = new TrueManNode();

        TrueManNode current = head.next;
        while(current!=null) {
            TrueManNode next = current.next;
            current.next = newHead.next; //修改了当前遍历到的结点的后继元素地址，故要用变量提前记录下更改前的地址值
            newHead.next = current;
            current = next; //最后还原回原链表中的结点地址值，用于原链表的遍历
        }
        //讲新head的内存结点赋值给旧的表头地址变量
        head = newHead;
        return this;
    }
}
