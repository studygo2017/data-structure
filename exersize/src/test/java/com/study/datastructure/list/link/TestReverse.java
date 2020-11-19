package com.study.datastructure.list.link;

import com.study.dataStructure.list.link.single.SingleLinkedList;
import com.study.dataStructure.list.link.single.SingleSortLinkedList;
import com.study.dataStructure.list.link.single.TrueManNode;
import com.study.dataStructure.list.link.single.homework.SglLinkedQuestion;
import org.junit.Test;

public class TestReverse {



    @Test
    public void testLink(){
//        SglLinkedQuestion.print();
       /* SingleLinkedList list = new SingleLinkedList();
        TrueManNode node = new TrueManNode(1, "及时雨", "宋江");
        TrueManNode node2 = new TrueManNode(2, "智多星", "吴用");
        TrueManNode node3 = new TrueManNode(3, "入云龙", "公孙胜");
        list.add(node);
        list.add(node2);
        list.add(node3);

        System.out.println(list.size());
        System.out.println(list);

        SglLinkedQuestion.reverseLink(list);
        System.out.println(list);*/

        SingleSortLinkedList list = new SingleSortLinkedList();
        TrueManNode node = new TrueManNode(1, "及时雨", "宋江");
        TrueManNode node2 = new TrueManNode(2, "智多星", "吴用");
        TrueManNode node3 = new TrueManNode(3, "入云龙", "公孙胜");
        TrueManNode node5 = new TrueManNode(5, "豹子头", "林冲");

        list.add(node5);
        list.add(node3);
        list.add(node);
        list.add(node2);

        System.out.println(list);
    }
}
