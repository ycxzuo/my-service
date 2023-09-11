package com.yczuoxin.myservice.concurrent;

import org.junit.jupiter.api.Test;

class ConcurrentTest {

    int count = 10000001;

    @Test
    void testConcurrent() throws InterruptedException {
        concurrency();
        serial();
    }

    private void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(() -> {
            int a = 0;
            for (int i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread.start();

        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("cost time: " + time + "ms, b = " + b);
    }

    private void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("cost time: " + time + "ms, b = " + b);
    }
}
