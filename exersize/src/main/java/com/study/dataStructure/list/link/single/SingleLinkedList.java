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

    /**
     * 添加结点
     * @return
     */
    public boolean add(TrueManNode node){
        if(isEmpty()) {
            head = node;
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
        StringBuilder sb = new StringBuilder("["+head.toString());
        TrueManNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            sb.append("   ").append(temp.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty(){
        return head == null || head.next == null;
    }

}
