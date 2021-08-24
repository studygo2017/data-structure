package com.study.dataStructure.util;

/**
 * 数组工具类
 */
public class ArraysUtil {

    /**
     * 生成指定长度length大小,元素范围在[0,scope)内的整数数组
     * @param length 
     * @param scope
     * @return
     */
    public static int[] createRandomArr(int length, int scope) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) ( Math.random() * scope ) ;
        }
        return arr;
    }

}
