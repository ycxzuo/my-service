package com.yczuoxin.myservice.collect;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class PrimeCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    /**
     * 生成返回数据的容器
     *
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    /**
     * 数据处理方式
     *
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (primes, candidate) -> primes.get(isPrime(primes.get(true), candidate)).add(candidate);
    }

    /**
     * 数据整合方法
     *
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map, map1) -> {
            map.get(true).addAll(map1.get(true));
            map.get(false).addAll(map1.get(false));
            return map;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.IDENTITY_FINISH);
    }

    public static boolean isPrime(List<Integer> primes, Integer candidate) {
        int sub = (int) Math.sqrt(candidate);
        return takeWhile(primes, i -> i <= sub).stream().noneMatch(p -> candidate % p == 0);
    }

    private static <E> List<E> takeWhile(List<E> list, Predicate<E> e) {
        int i = 0;
        for (E item : list) {
            if (!e.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
