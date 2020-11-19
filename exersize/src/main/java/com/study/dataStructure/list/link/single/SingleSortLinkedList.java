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
        TrueManNode temp = head; //头结点
        if(isEmpty()) {
            temp.next = node;
            return true;
        }
        //遍历直到找到大于当前no的
        while (true){
            if(temp.next.no == node.no) throw new RuntimeException("已存在");

            if(temp.next==null || temp.next.no > node.no){ //temp的下一个no已经大于node的no，故要放到temp后面去
                break;
            }else{
                temp = temp.next;
            }
        }
        node.next = temp.next;
        temp.next = node;
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
}
