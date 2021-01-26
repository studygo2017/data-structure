package com.study.dataStructure.list.array;

import com.study.dataStructure.exception.IndexOutOfArrayExceptiom;
import com.study.dataStructure.exception.NoMoreElementInArray;
import com.study.dataStructure.list.MyIterator;
import com.study.dataStructure.list.MyList;

public class MyArrayList<E> implements MyList<E> {

    private final static int DEFAULT_CAPACITY = 10;

    private E[] arr;//基础数组

    private int capacity; //当前基础数组的容量

    private int size ; //当前动态数组的长度

    public MyArrayList(int initCapacity){
        doClear(initCapacity);
    }

    public MyArrayList(){
        doClear(DEFAULT_CAPACITY);
    }

    public boolean add(E e) {
        //是否要扩容？
        ensureCapacity(size+1);
        arr[size++] = e;
        return true;
    }

    public boolean add(int index, E e) {
        checkIndexIllegal(index);
        ensureCapacity(size+1);
        for (int i = size-1; i >= index; i--) {
            arr[i+1] = arr[i];
        }
        arr[index] = e;
        size++;
        return true;
    }

    public E remove(int index) {
        checkIndexIllegal(index);
        E e = arr[index];
        for (int i = index+1; i < size; i++) {
            arr[i-1] = arr[i];
        }
        size--;
        ensureCapacity(size+1); //是否需要缩容
        return e;
    }

    public boolean set(int index, E e) {
        checkIndexIllegal(index);
        arr[index] = e;
        return true;
    }

    public E get(int index) {
        checkIndexIllegal(index);
        return arr[index];
    }

    public int size() {
        return size;
    }

    /**
     * 清空数组
     */
    public void clear(){
       doClear(DEFAULT_CAPACITY);
    }

    private void doClear(int initCapacity){
        arr = (E[]) new Object[initCapacity];
        this.size = 0;
    }

    /**
     * 扩容与缩容的处理
     * 扩容缩容要注意时间复杂的震荡的问题！
     * @param needCapacity 当前需要的存储容量
     */
    private void ensureCapacity(int needCapacity) {
        if(needCapacity <= capacity) return;

        int newCapacity = needCapacity;
        if( needCapacity<<3 < capacity ) {  //容量四倍于所需以上，缩容；
            newCapacity = capacity >> 2;
        }

        E[] newArr = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /**
     * 检测参数中的索引是否合法
     */
    private void checkIndexIllegal(int index) {
        if(index < 0 || index > size-1){
            //抛出自定义异常
            throw new IndexOutOfArrayExceptiom("数组索引越界，非法的index："+index);
        }
    }

    private class ArrayListIterator<E> implements MyIterator<E>{
        private int current = 0; //当前迭代到的索引位置

        public boolean hasNext() {
            return current < size;
        }

        public E next() {
            if(current > size-1){
                throw new NoMoreElementInArray();
            }
            return (E) arr[current++];
        }

        public E remove() {
            checkIndexIllegal(current-1);
            return (E) MyArrayList.this.remove(--current);
        }
    }

    public MyIterator<E> iterator(){
        return new ArrayListIterator();
    }

    public boolean contain(E item){
        if(item == null) return false;
        for (int i = 0; i < size; i++) {
            if(arr[i] !=null && arr[i].equals(item) ){
                return true;
            }
        }
        return false;
    }

    public int indexOf(E item){
        if(item == null) return -1;
        for (int i = 0; i < size; i++) {
            if(arr[i] !=null && arr[i].equals(item) ){
                return i;
            }
        }
        return -1;
    }
}
