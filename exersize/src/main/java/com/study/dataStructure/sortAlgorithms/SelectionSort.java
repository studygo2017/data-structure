package com.study.dataStructure.sortAlgorithms;

import com.study.dataStructure.util.ArraysUtil;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        //生成一个指定长度,指定数字范围(小于)的随机数组
        int[] nums = ArraysUtil.createRandomArr(10,100);
        System.out.println(Arrays.toString(nums));
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length-1 ; i++) {
            int min = nums[i];
            int minIndex = i;
            for (int n = i+1; n < nums.length; n++) {
                if(nums[n] < min){
                    min = nums[n];
                    minIndex = n;
                }
            }
            //替换最小值
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
    }

}
