package com.study.dataStructure.util;

import com.study.dataStructure.list.MyList;
import com.study.dataStructure.list.array.MyArrayList;

public class MyCollectionUtil {
    public static <T> MyList<T> toList(T[] arr, Class<T> clazz) {
        MyList<T> list = new MyArrayList<T>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public static  MyList<String> toStrListIgnoreNull(String[] arr) {
        MyList<String> list = new MyArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            String str = arr[i];
            if( isNullString(str) ) continue;
            list.add(arr[i]);
        }
        return list;
    }

    private static boolean isNullString(String str) {
        if(str == null ) return true;
        for (String s : str.split("")) {
            if(s == null || "".equals(s) || " ".equals(s)) return true;
        }
        return false;
    }
}
