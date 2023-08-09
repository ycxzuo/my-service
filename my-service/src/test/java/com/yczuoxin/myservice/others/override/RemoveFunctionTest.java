package com.yczuoxin.myservice.others.override;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RemoveFunctionTest {

    @Test
    void testRemove() {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = -3; i < 3; i++) {
            list.add(i);
            set.add(i);
        }

        for (int i = 0; i < 3; i++) {
            list.remove(i);
            set.remove(i);
        }

        System.out.println(list + " " + set);
    }

}
