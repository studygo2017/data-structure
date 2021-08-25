package com.study.dataStructure.sortAlgorithms;

import com.study.dataStructure.util.ArraysUtil;
import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        //生成一个指定长度,指定数字范围(小于)的随机数组
        int[] nums = ArraysUtil.createRandomArr(100,1000);
        System.out.println(Arrays.toString(nums));
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void bubbleSort(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length-1 ; i++) {
            boolean flag = false;
            for (int y = 0 ; y < nums.length-1-i ; y++) {
                if(nums[y]>nums[y+1]){
                    int temp = nums[y];
                    nums[y] = nums[y+1];
                    nums[y+1] = temp;
                    flag = true;
                }
            }
            if( flag ) count++;
            else {
                System.out.printf("一共排序了%d趟\n",count);
                return;
            }
        }
        System.out.printf("一共排序了%d趟",count);
    }

}
