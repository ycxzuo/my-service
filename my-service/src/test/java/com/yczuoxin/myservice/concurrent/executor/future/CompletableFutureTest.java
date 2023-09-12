package com.yczuoxin.myservice.concurrent.executor.future;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * CompletableFuture 与 Stream 并行流的区别：
 *  completableFuture 可以自定义线程池
 *  Stream 使用的默认的 ForkJoinPool，核心线程数跟CPU核心数有关，不能自定义
 */
class CompletableFutureTest {

    SecureRandom secureRandom = new SecureRandom();

    private double calculatePrice(String productName) {
        delay();
        return secureRandom.nextDouble() * productName.charAt(0) + productName.charAt(1);
    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private double getPrice(String productName) {
        return calculatePrice(productName);
    }

    private Future<Double> getPriceAsync(String productName) {
        // 该对象完成异步执行后会读取调用生产者方法的返回值
        return CompletableFuture.supplyAsync(() -> calculatePrice(productName));
    }

    @Test
    void testCompletableFuture() throws ExecutionException, InterruptedException {
        String productName = "computer";
        long time1 = completableTest(productName);
        long time2 = simpleTest(productName);
        Assertions.assertTrue(time1 < time2);
    }

    private long simpleTest(String productName) {
        long start = System.nanoTime();
        double price = getPrice(productName);
        delay();
        long time = System.nanoTime() - start;
        System.out.println("get price: " + price + ", takes " + time + "ms");
        return time;
    }

    private long completableTest(String productName) throws InterruptedException, ExecutionException {
        long start = System.nanoTime();
        Future<Double> priceAsync = getPriceAsync(productName);
        delay();
        Double price = priceAsync.get();
        long time = System.nanoTime() - start;
        System.out.println("get price async: " + price + ", takes " + time + "ms");
        return time;
    }
}
