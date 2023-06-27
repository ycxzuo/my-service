package com.yczuoxin.myservice.clone;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

class CloneTest {

    @Test
    void cloneTest() throws CloneNotSupportedException {
        CloneEntity entity = new CloneEntity();
        entity.arr = new int[]{1, 2, 3};
        CloneEntity clone = entity.clone();
        clone.arr[1] = 4;
        Assert.equals(entity.arr[1], 2);
    }

}
