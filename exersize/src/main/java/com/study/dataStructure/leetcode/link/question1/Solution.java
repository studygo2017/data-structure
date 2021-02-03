package com.study.dataStructure.leetcode.link.question1;

import javax.management.RuntimeMBeanException;

/**
 * 对指定链表清除其中所有的指定元素
 * 如 1->2->6->5->6->7->8   清除6   得到 1->2->5->7->8
 */
public class Solution {

    ListNode listNode = getListNode(new int[]{1, 2, 6, 5, 6, 7, 8});

    public ListNode rmElemsFromLink(ListNode listNode, int num){

        while (listNode!=null && listNode.val == num ){
            listNode = listNode.next;
        }

        if(listNode == null || listNode.next == null) return null;

        ListNode prev = listNode;
        while (prev.next != null){
            if(prev.next.val == num){
                prev.next = prev.next.next;
            }else{
                prev = prev.next;
            }
        }
        return listNode;
    }

    public ListNode getListNode(int[] arr){
        if( arr==null || arr.length == 0 ) throw new RuntimeException("参数为空");
        ListNode head = new ListNode(arr[0]);
        if(arr.length == 1) return head;

        ListNode temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }
        return head;
    }
}
