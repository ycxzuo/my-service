package com.yczuoxin.myservice.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ListTest {

    @Test
    void removeRangeTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        // subList 操作的是原 List
        List<Integer> integers = list.subList(0, 4);
        integers.clear();
        Assertions.assertEquals(3, list.size());
    }

}
