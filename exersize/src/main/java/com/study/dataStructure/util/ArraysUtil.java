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

    /**
     * 判断数组是否是(按大小)排好序的
     * @param nums
     * @return
     */
    public static boolean isSort(int[] nums){
        if(nums.length>=0 && nums.length<=2) return true;
        Boolean flag = null ;
        for (int i = 1; i < nums.length; i++) {
            boolean thisFlag = nums[i] - nums[i-1] >= 0;
            if(flag == null)
                flag =  thisFlag;
            else if(flag != thisFlag)
                return false;
        }
        return true;
    }

}
