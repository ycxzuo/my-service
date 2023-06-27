package com.yczuoxin.myservice.clone;

/**
 * 类中数组类型的 clone 方式
 */
public class CloneEntity implements Cloneable{

    public int[] arr;

    @Override
    protected CloneEntity clone() throws CloneNotSupportedException {
        CloneEntity entity = (CloneEntity) super.clone();
        entity.arr = arr.clone();
        return entity;
    }
}
