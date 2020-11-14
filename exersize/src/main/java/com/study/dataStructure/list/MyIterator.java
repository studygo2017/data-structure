package com.study.dataStructure.list;

/**
 * 自定义迭代器接口
 * @param <E>
 */
public interface MyIterator<E> {

    boolean hasNext();

    E next();

    /**
     * 删除迭代到的当前项
     * @return
     */
    E remove();

}
