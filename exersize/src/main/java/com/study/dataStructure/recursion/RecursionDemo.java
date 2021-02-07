package com.study.dataStructure.recursion;

public class RecursionDemo {

    public static void main(String[] args) {
        int[][] map = initMap();
    }

    /**
     * 初始化地图
     */
    public static int[][] initMap(){
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1(第一行到倒数第二行)
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置中间的挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][2] = 1;
        map[2][2] = 1;
        return map;
    }


}
