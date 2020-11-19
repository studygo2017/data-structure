package com.study.dataStructure.list.link.single;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 链表结点
 */
@Data
public class TrueManNode {

     public int no;//好汉座次

     public String nickname; //好汉绰号

     public String name;//好汉姓名

     public TrueManNode next;//指针（指向之后的结点）

     @Override
     public String toString(){
          return new StringBuilder("梁山好汉:"+no).append(" - ").append(nickname).append("-").append(name).toString();
     }

     public TrueManNode(int no,String nickname,String name){
          this.no = no;
          this.nickname = nickname;
          this.name = name;
     }

     public TrueManNode(){}
}
