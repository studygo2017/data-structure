package com.study.dataStructure.list.stack.probleams;

import com.study.dataStructure.list.MyIterator;
import com.study.dataStructure.list.MyList;
import com.study.dataStructure.list.array.MyArrayList;
import com.study.dataStructure.list.stack.ArrayStack;
import com.study.dataStructure.util.MyCollectionUtil;
import lombok.var;

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

    public static void main(String[] args) {
        new CalStack().calTest();

    }

    private void calTest() {
//        String expression = sysIn();
//        System.out.println(new CalStack().calExpression(expression));
        String expression = "7 - 10 * 8 / 40 / 2 * 3 + 7 * 5 - 2 * 10";
        System.out.println(new CalStack().calExpression(expression));
    }

    private String sysIn() {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        System.out.println("==============正整数加减乘除四则运算==============");
        while(true){

            System.out.println(sb.toString() + "   请输入一个正整数：");

            int num = sc.nextInt();
            sb.append(num + " ");

            while(true){
                String str = null;
                System.out.println(sb.toString() + "   请输入一个四则运算符号：");
                if(sb.toString().split(" ").length >= 3){
                    System.out.println("=====或按q结束输入=====");
                    str = sc.next();
                    if("q".equals(str)) {
                        return sb.substring(0, sb.length() - 1).toString();
                    }
                }

                String next = str == null ? sc.next() : str;

                if(!isOperator(next) ){
                    System.out.println("输入有误，请重新输入：");
                    continue;
                }else{
                    sb.append(next+" ");
                    break;
                }
            }


        }
//        String expression = "7 + 5 * 8 - 6 * 5 / 3  + 9 * 3 * 5 - 100"; // 72
//        String[] expArr = expression.split(" ");
//        int ret = new CalStack().calExpression("7 + 5 * 8 - 6 * 5");
//        System.out.println(ret);
        /*MyIterator<String> it =  MyCollectionUtil.toList(expArr,String.class).iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }*/
//        System.out.println(new CalStack().calExpression(expression));
    }

    /**
     * 正整数加减乘除运算的表达式 表达式间每个数字/字符要用空格隔开
     * @return
     */
    public  int calExpression(String expression){
        MyList<String> expreList = MyCollectionUtil.toStrListIgnoreNull(expression.split(" "));


        MyIterator<String> iterator = expreList.iterator();
        while (iterator.hasNext()){
            String ch = iterator.next();
            if( isNumeric(ch) ){
                //数字放在数字栈
                numStack.push(ch);
                if( !iterator.hasNext()) {
                    getNumCharAndCalPush();

                    break;
                }
            }else if( isOperator(ch) ){ //是运算符
                //判断符号栈中有符号没
                if( charStack.isEmpty() ){
                    //符号栈中没符号，直接放入
                    charStack.push(ch);

                }else{
                    //判断当前符号和栈内符号的优先级大小
                    if( gt(ch,charStack.peek()) ){
                        //如果大于,则先计算数字栈栈顶和下一个数字
                        String calNum = cal(numStack.pop(), iterator.next(), ch);
                        numStack.push(calNum);
                    }else{
                        //不大于则先计算之前的
                        getNumCharAndCalPush();
                        charStack.push(ch);
                    }

                }
            }else{
                throw new RuntimeException("不是正整数也不是符号");
            }
        }
        if(numStack.size()>1)  getNumCharAndCalPush();
        return Integer.parseInt( numStack.pop() );
    }

    private void getNumCharAndCalPush() {
        String num2 = numStack.pop();
        String num = numStack.pop();
        String calChar = charStack.pop();
        numStack.push( cal(num,num2,calChar) );
    }


    private String cal(String num, String num2, String nowCalChar) {
        int chIndex = itemIndexOfArr(nowCalChar, operArr);
        if( chIndex == -1 ){
            throw new RuntimeException("符号非法");
        }
        int num_1 = Integer.parseInt(num);
        int num_2 = Integer.parseInt(num2);
        switch (chIndex){
            case 0: // +
                return String.valueOf(num_1 + num_2);
            case 1: // -
                return String.valueOf(num_1 - num_2);
            case 2: // *
                return String.valueOf(num_1 * num_2);
            case 3: // /
                return String.valueOf(num_1 / num_2);
            default:
                throw new RuntimeException("符号非法");
        }
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
        if(ch.length()!=1) return false;
        for (String oper : operArr) {
            if( ch != null && ch.equals(oper) ) return true;
        }
        return false;
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
