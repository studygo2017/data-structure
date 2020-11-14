package com.study.dataStructure.list;

public interface MyList<E> {

    boolean add(E e);

    boolean add(int index,E e);

    E remove(int index);

    boolean set(int index,E e);

    E get(int index);

    int size();

}
