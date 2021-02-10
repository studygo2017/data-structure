package com.study.dataStructure.recursion;

import java.util.Arrays;

public class RecursionDemo {

    public static void main(String[] args) {
        int[][] map = initMap();
        printMap(map);

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
        return map;
    }

    private static void printMap(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int i1 = 0; i1 < map[i].length; i1++) {
                System.out.print(map[i][i1] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 按下->右->上->左（回溯）策略走迷宫
     * 迷宫终点为map[6][5]
     * @param map 迷宫数据
     * @param i 起始位置
     * @param j
     */
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){ //终点已到达
            return true;
        }else {
            if(map[i][j] == 0){

            }else{ // 1墙 2已走过且可以走通  3已走过但走不通

                return false;
            }

        }

        return false;
    }

}
