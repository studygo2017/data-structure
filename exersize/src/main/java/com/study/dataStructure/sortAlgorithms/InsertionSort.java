package com.study.dataStructure.sortAlgorithms;

import com.study.dataStructure.util.ArraysUtil;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] nums = ArraysUtil.createRandomArr(20,1000);
        System.out.println(Arrays.toString(nums));
        System.out.println( ArraysUtil.isSort(nums) );
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println( ArraysUtil.isSort(nums) );
    }

    private static void insertionSort(int[] nums) {
        //迭代未排序序列,与已排序的每一个元素逐步比较
        for (int i = 1; i < nums.length; i++) {  //未排序序列从第二个元素即索引为1的开始
            for (int j = 0 ; j < i ; j++) {
                if(nums[i] < nums[j]){
                    int temp = nums[i];

                    for (int k = i; k > j ; k--) {
                        nums[k] = nums[k-1];
                    }
                    nums[j] = temp;
                    break;
                }
            }
        }
    }

}
