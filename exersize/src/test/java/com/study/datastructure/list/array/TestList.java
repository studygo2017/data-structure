package com.study.datastructure.list.array;

import com.study.dataStructure.list.MyIterator;
import com.study.dataStructure.list.array.MyArrayList;
import org.junit.Test;

public class TestList {

    @Test
    public void TestMyArrayList(){
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            list.add(i+1);
        }
        /*System.out.println(list.size());
        System.out.println(list.get(50));
        System.out.println(list.remove(16));
        System.out.println(list.size());

        System.out.println("=========================================");

        boolean set = list.set(98, 1000);
        System.out.println(list.get(98));*/

        MyIterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println("next = "+iterator.next());
            System.out.println("remove = "+iterator.remove());
        }

        System.out.println("size = "+list.size());

    }
}
