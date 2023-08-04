package com.yczuoxin.myservice.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConstructTest {

    @Test
    void construct(){
        List<Integer> weights= Arrays.asList(130, 150 ,170);
        List<Apple> apples = make(weights, Apple::new);
        for (Apple apple : apples) {
            System.out.println(apple);
        }
    }

    @Test
    void biConstruct(){
        Map<String, Integer> map = new HashMap<>();
        map.put("RED", 160);
        map.put("GREEN", 160);
        List<Apple> apples = make(map, Apple::new);
        for (Apple apple : apples) {
            System.out.println(apple);
        }
    }

    @Test
    void tripleTest() {
        List<double[]> collect = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .boxed()
                        .map(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(arr -> arr[2] % 1 == 0))
                .limit(5).collect(Collectors.toList());
        for (double[] doubles : collect) {
            System.out.println(doubles[0] + "," + doubles[1] + "," + doubles[2]);
        }
    }

    List<Apple> make(Map<String, Integer> map, BiFunction<String, Integer, Apple> function) {
        return map.entrySet().stream().map(entry -> function.apply(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }


    List<Apple> make(List<Integer> list, Function<Integer, Apple> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }

}
