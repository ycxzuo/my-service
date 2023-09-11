package com.yczuoxin.myservice.concurrent.deadlock;

import org.junit.jupiter.api.Test;

class DeadLockTest {

    final String a = "A";

    final String b = "B";

    @Test
    void testDeadLock() throws InterruptedException {
        deadLock();
    }

    void deadLock() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("1");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (b) {
                synchronized (a) {
                    System.out.println("2");
                }
            }
        });
        t1.start();
        t2.start();
        // 等待执行完毕
        t1.join();
    }
}
