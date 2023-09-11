package com.yczuoxin.myservice.concurrent.reordering;

import org.junit.jupiter.api.Test;

class ReorderingTest {

    static int x = 0, y = 0;

    static int a = 0, b = 0;

    @Test
    void testReordering() throws InterruptedException {
        while (true) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            boolean reordering = reSort();
            if (reordering) {
                System.out.println("(" + x + "," + y + ")");
                break;
            }
        }
    }
    private boolean reSort() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread thread2 = new Thread(() -> {
            b = 1;
            y = a;
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        return x == 0 && y == 0;
    }
}
