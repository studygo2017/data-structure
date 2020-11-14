package com.study.dataStructure.exception;

/**
 * 自定义异常
 */
public class IndexOutOfArrayExceptiom extends RuntimeException {

    public IndexOutOfArrayExceptiom(){
        super();
    }

    public IndexOutOfArrayExceptiom(String message){
        super(message);
    }
}
