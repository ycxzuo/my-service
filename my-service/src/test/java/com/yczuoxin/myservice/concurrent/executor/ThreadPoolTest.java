package com.yczuoxin.myservice.concurrent.executor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadPoolTest {

    @Test
    void cachedThreadPoolTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        execute(executorService);
    }

    @Test
    void fixedThreadPoolTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        execute(executorService);
    }

    @Test
    void stealingThreadPoolTest() {
        ExecutorService executorService = Executors.newWorkStealingPool();
        execute(executorService);
    }

    void execute(ExecutorService executorService) {
        for (int i = 0; i < 20; i++) {
            System.out.println(executorService);
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(executorService);
        while (executorService.isTerminated()) {
            executorService.shutdown();
        }
    }

}
