package com.study.dataStructure.list.array;

import com.study.dataStructure.list.MyList;

/**
 * MyList操作工具类
 */
public class MyListUtil {
    public static <T> MyList<T> asList(Object[] arr,Class<T> clazz){
        MyList<T> list = new MyArrayList<T>();
        for (Object item : arr) {
            list.add( (T)item );
        }
        return list;
    }

    public static void main(String[] args) {
        String[] arr = {"a", "b", "c"};
        System.out.println(MyListUtil.asList(arr, String.class));
    }
}
