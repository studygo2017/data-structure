package com.study.dataStructure.list.stack.probleams;

import com.study.dataStructure.list.MyList;
import com.study.dataStructure.list.array.MyListUtil;
import com.study.dataStructure.list.stack.ArrayStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 前缀表达式计算
 */
public class CalStack {

    private ArrayStack<String> numStack = new ArrayStack<String>(); //数字栈

    private ArrayStack<String> charStack = new ArrayStack<String>(); //符号栈

    private static final List<String> charList = new ArrayList<String>(){{
        add("+");
        add("-");
        add("*");
        add("/");
    }};
    /**
     * 正整数加减乘除运算的表达式 表达式间每个数字/字符要用空格隔开
     * @return
     */
    public  int calExpression(String expression){
        List<String> expreList = Arrays.asList(expression.split(" "));
        for (String ch : expreList) {
            if( isNumeric(ch) ){
                //数字放在数字栈
                numStack.push(ch);
            }else if( isOperator(ch) ){ //是运算符
                //判断符号栈中有符号没
                if( charStack.isEmpty() ){
                    //符号栈中没符号，直接放入
                    charStack.push(ch);
                }else{
                    //符号栈中有符号,判断优先级
                    if( gt( ch,charStack.peek() ) ){
                        //如果高于之前的优先级

                    }else {
                        //如果不高于之前的优先级，那就对之前的进行计算
                        int num = Integer.parseInt(numStack.pop());
                        int num2 = Integer.parseInt(numStack.pop());
                        if( !numStack.isEmpty() ) throw new RuntimeException("此时数栈应为空");
                        String nowCalChar = charStack.pop();
                        if( !charStack.isEmpty() ) throw new RuntimeException("此时符号栈应为空");
                        int totalNum = cal(num,num2,nowCalChar);
                    }
                }
            }else{
                throw new RuntimeException("不是正整数也不是符号");
            }
        }
        return 0;
    }

    private int cal(int num, int num2, String nowCalChar) {
        int chIndex = itemIndexOfArr(nowCalChar, operArr);
        if( chIndex <= -1 ){
            throw new RuntimeException("符号非法");
        }

        return 0;
    }

    private boolean gt(String ch, String lastCh) {
        int[] optVals = new int[]{1,1,2,2}; //加减乘除的优先级值
        return optVals[itemIndexOfArr(ch,operArr)] > optVals[itemIndexOfArr(lastCh,operArr)];

    }

    private int itemIndexOfArr(String item,String[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(item!=null && item.equals(arr[i])) return i;
        }
        return -1;
    }


    private final static String[] operArr = new String[]{"+","-","*","/"};


    /**
     * 判断字符串是不是加减乘除运算符
     */
    private boolean isOperator(String ch) {
        for (String oper : operArr) {
            if( ch != null && ch.equals(oper) ) return true;
        }
        return false;
    }

    private static String checkStr(String str) {
        if( charList.contains(str) ){

        }
        return null;
    }

    public static void main(String[] args) {

        int ret = new CalStack().calExpression("7 + 5 * 8 - 6 * 5");
    }



    /**
     * 用正则来判断一个字符串是不是正整数
     * @param numStr
     * @return
     */
    private static boolean isNumeric(String numStr){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(numStr).matches();
    }


}
