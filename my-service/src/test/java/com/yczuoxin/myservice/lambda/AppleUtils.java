package com.yczuoxin.myservice.lambda;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppleUtils {

    public static List<Apple> filter(List<Apple> inventory, Predicate<Apple> predicate) {
        return inventory.stream().filter(predicate).collect(Collectors.toList());
    }

}
