package com.study.dataStructure.list.link.single.Josephus;

/**
 * 单向循环链表解决约瑟夫问题
 */
public class CircleLinkedList {

    private Boy first = null;

    /**
     * 往空链表加入指定个数的boy
     * @param num
     */
    public void addBoy(int num){
        if(num<1) throw new RuntimeException("加入个数异常");
        Boy curBoy = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy();
            boy.setNo(i);
            if(i == 1){ //加入第一个
                first = boy;
                boy.setNext(boy);
                curBoy = boy;
            }else{
                curBoy.next = boy;
                first = boy.next;
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历
     */
    public void showBoys(){
        if(first == null) throw new RuntimeException("为空");
        Boy curBoy = first;
        while(true){
            System.out.println(curBoy);
            if(curBoy.next == first) break; //已经遍历到最后一个了
            curBoy = curBoy.next;
        }
    }

    /**
     * 解决约瑟夫问题
     * @param startNo 开始数数的boy编号
     * @param count 数数的数量(第若干个boy出圈)
   * @param total 游戏开始前圈中的所有boy个数
     */
    public void dealJosephusQuestion(int startNo,int count,int total){
        if(first==null) throw new RuntimeException("为空");
        if(count<total) throw new RuntimeException("参数有误");
        Boy temp = first;
        while(true){
            temp = temp.next;
            if(temp.next == first) break; //末尾结点（头结点的前驱元素,单链表删除结点时需要的）
        }

        //数数前，先找出即将数数的boy结点和其前驱结点（末尾）
        for (int i = 0; i < startNo-1; i++) {
            first = first.next;
            temp = temp.next;
        }
        while (true){
            if( first == temp ) {
                System.out.println("编号"+first.no+"的小朋友出圈");
                first = null;
                break;
            }
            //找到数数后的待出圈boy结点,依靠其前驱结点，进行删除
            for (int i = 0; i < count - 1; i++) {
                first = first.next;
                temp = temp.next;
            }
            //出圈
            System.out.println("编号"+first.no+"出圈");
            first.next = temp.next;
            first = first.next;
        }
    }
}