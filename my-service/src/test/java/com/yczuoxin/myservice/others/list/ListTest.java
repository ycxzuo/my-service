package com.yczuoxin.myservice.others.list;

import com.yczuoxin.myservice.entity.Info;
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

    @Test
    void removeRangeTest2() {
        Info info1 = new Info();
        info1.setId(1L);
        info1.setValue("aaa");
        Info info2 = new Info();
        info2.setId(2L);
        info2.setValue("bbb");
        Info info3 = new Info();
        info3.setId(3L);
        info3.setValue("ccc");
        List<Info> list = Arrays.asList(info1, info2, info3);
        // subList 操作的是原 List
        List<Info> integers = list.subList(0, 4);
        integers.clear();
        Assertions.assertEquals(3, list.size());
    }

}
