package com.study.dataStructure.linearTable;

import java.util.Random;

/**
 * 稀疏数组
 */
public class SparseArray {

    /**
     * 生成一个用于压缩示例的 16*16二维数组
     */
    public int[][] initArraydemo(){
        int[][] arrDemo = new int[16][16]; //默认值为0
        arrDemo[6][8] = 1;
        arrDemo[7][9] = 2;
        arrDemo[14][15] = 3;
        return arrDemo;
    }

    /**
     * 原数组压缩为稀疏数组
     * @return 压缩后的数组
     */
    public int[][] transferToSparse(int[][] arr){
        int count = 0;//记录不同值个数

        for (int i = 0; i < arr.length; i++) {
            for (int i2 = 0; i2 < arr[i].length; i2++) {
                if(arr[i][i2] != 0){
                    count++;
                }
            }
        }
        //初始化压缩后的数组
        int[][] newArr = new int[count + 1][3];
        newArr[0][0] = arr.length;
        newArr[0][1] = arr[0].length;
        newArr[0][2] = count;

        int lines = 0;//记录不同值的行数
        for (int i = 0; i < arr.length; i++) {
            for (int i2 = 0; i2 < arr[i].length; i2++) {
                if(arr[i][i2] != 0){
                    lines++;
                    newArr[lines][0] = i;
                    newArr[lines][1] = i2;
                    newArr[lines][2] = arr[i][i2];
                }
            }
        }
        return newArr;
    }

    /**
     * 将压缩后的稀疏数组还原为原数组
     */
    public int[][] rtnToArrSource(int[][] arr){
        if(arr.length<1 || arr[0].length != 3){
            throw new RuntimeException("压缩数组数据有误!");
        }
        int[][] arrSource = new int[arr[0][0]][arr[0][1]];
        for (int i = 1; i <= arr[0][2]; i++) {
            arrSource[arr[i][0]][arr[i][1]] = arr[i][2];
        }
        return arrSource;
    }

    /**
     * 打印数组
     * @param arr
     */
    private void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = 0; i1 < arr[i].length; i1++) {
                System.out.printf("%d\t",arr[i][i1]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SparseArray demo = new SparseArray();
        int[][] arrSource = demo.initArraydemo();
        demo.printArr(arrSource);
        System.out.println();
        int[][] transferedArr = demo.transferToSparse(arrSource);
        demo.printArr(transferedArr);
        System.out.println();
        demo.printArr(demo.rtnToArrSource(transferedArr));
    }

}
