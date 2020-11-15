package com.study.datastructure.list.queue;

import com.study.dataStructure.queue.CircleQueue;
import org.junit.Test;

public class TestCircleQueue {

    @Test
    public void testQueue(){

        CircleQueue queue = new CircleQueue(100);
        for (int i = 0; i < 100; i++) {
            queue.add(i);
        }
        queue.showQueue();

        for (int i = 0; i < 20; i++) {
            queue.remove();
        }
        System.out.println("==========================================================");
        queue.showQueue();

        System.out.println("此时的队列首元素："+queue.peek());
        System.out.println(queue.size());

        for (int i = 0; i < 20; i++) {
            queue.add(100+i);
        }
        System.out.println("================================================================");
        queue.showQueue();
    }
}
