package com.yczuoxin.myservice.clone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CloneTest {

    @Test
    void cloneTest() throws CloneNotSupportedException {
        CloneEntity entity = new CloneEntity();
        entity.arr = new int[]{1, 2, 3};
        CloneEntity clone = entity.clone();
        clone.arr[1] = 4;
        Assertions.assertEquals(2, entity.arr[1]);
    }

}
