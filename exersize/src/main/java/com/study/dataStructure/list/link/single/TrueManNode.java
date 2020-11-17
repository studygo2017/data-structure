package com.study.dataStructure.list.link.single;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 链表结点
 */
@Data
@NoArgsConstructor
public class TrueManNode {

     public int no;//好汉座次

     public String nickname; //好汉绰号

     public String name;//好汉姓名

     public TrueManNode next;//指针（指向之后的结点）

     @Override
     public String toString(){
          return new StringBuilder("好汉座次:"+no).append(" - ").append(nickname).append("-").toString();
     }
}
