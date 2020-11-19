package com.study.dataStructure.list.link.single;

/**
 * 结点排序的基础单链表
 */
public class SingleSortLinkedList {

    /**
     * 头结点
     */
    private TrueManNode head = new TrueManNode();

    /**
     * 按座次顺序添加结点
     * @return
     */
    public boolean add(TrueManNode node){
        TrueManNode temp = head; //指向头结点

        //循环直到找到大于参数结点编号的结点
        while(true){
            if(temp.next == null){ //已经到了末尾或链表本身就为空;加上
                break;
            }
            if(temp.next.no == node.no){ //已经存在
                throw new RuntimeException("已经存在");
            }
            if(temp.next.no > node.no){ //找到了
                break;
            }
            temp = temp.next;
        }
        node.next = temp.next;
        return true;
    }

    public void remove(TrueManNode node){
        TrueManNode temp = head; //指向头结点
        while(temp.next!=null){
            if(temp.next.no == node.no){
                temp.next = temp.next.next; //没有指针指向这个结点，自然便会被gc
                return ;
            }
            temp = temp.next;
        }
        throw new RuntimeException("没有这个结点");
    }

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
