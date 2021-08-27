package com.study.dataStructure.sortAlgorithms;

import com.study.dataStructure.util.ArraysUtil;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] nums = ArraysUtil.createRandomArr(200,1000);
        System.out.println(Arrays.toString(nums));
        System.out.println( ArraysUtil.isSort(nums) );
        shellSort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println( ArraysUtil.isSort(nums) );
    }

    private static void shellSort(int[] nums) {
//        int gap = nums.length/2;
        for(int gap = nums.length/2; gap>=1 ; gap = gap/2){ //一直到gap为1,才"整合"了每个分组排序后的结果
            for (int i = gap; i < nums.length ; i++) {
                for( int j = i; j>=gap; j-=gap ){
                    if( nums[j] < nums[j-gap] ){
                        ArraysUtil.swap(nums,j,j-gap);
                    }
                }
            }
        }
    }

}
