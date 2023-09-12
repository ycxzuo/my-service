package com.yczuoxin.myservice.lambda;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AppleTest {

    private int x = 0;

    @Test
    void filterTest() {
        List<Apple> list = Arrays.asList(new Apple("RED", 160), new Apple("GREEN", 170), new Apple("RED", 120));
        list.sort((a, b) -> {
            System.out.println(x++);
            if (a.getColor().equals(b.getColor())) return 0;
            if ("RED".equals(a.getColor())) return 1;
            else return -1;
        });
        list.forEach(System.out::println );
        AppleUtils.filter(list, (apple -> "RED".equals(apple.getColor()) && apple.getWeight() > 150)).forEach(System.out::println);
    }

}
