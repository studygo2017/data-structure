package com.study.datastructure.list.link;

import com.study.dataStructure.list.link.single.SingleLinkedList;
import com.study.dataStructure.list.link.single.SingleSortLinkedList;
import com.study.dataStructure.list.link.single.TrueManNode;
import com.study.dataStructure.list.link.single.homework.SglLinkedQuestion;
import org.junit.Test;

public class TestReverse {

    TrueManNode node = new TrueManNode(1, "及时雨", "宋江");
    TrueManNode node2 = new TrueManNode(2, "玉麒麟", "卢俊义");
    TrueManNode node3 = new TrueManNode(3, "智多星", "吴用");
    TrueManNode node4 = new TrueManNode(4, "入云龙", "公孙胜");
    TrueManNode node5 = new TrueManNode(5, "豹子头", "林冲");
    TrueManNode node6 = new TrueManNode(6, "霹雳火", "秦明");
    TrueManNode node7 = new TrueManNode(7, "双枪将", "董平");

    @Test
    public void testLink(){
//        SglLinkedQuestion.print();
        SingleLinkedList list = new SingleLinkedList();

        list.add(node);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);

        System.out.println(list.size());
        System.out.println(list);

        SglLinkedQuestion.reverseLink(list);
        System.out.println(list);

        /*SingleSortLinkedList list = new SingleSortLinkedList();
        TrueManNode node = new TrueManNode(1, "及时雨", "宋江");
        TrueManNode node2 = new TrueManNode(2, "智多星", "吴用");
        TrueManNode node3 = new TrueManNode(3, "入云龙", "公孙胜");
        TrueManNode node5 = new TrueManNode(5, "豹子头", "林冲");

        list.add(node5);
        list.add(node3);
        list.add(node);
        list.add(node2);

        list.remove(node3);
        System.out.println(list);*/
    }

    @Test
    public void testRev() {
        SingleLinkedList list = new SingleLinkedList();
        list.add(node);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }
}
