package com.yczuoxin.myservice.concurrent.reordering;

import org.junit.jupiter.api.Test;

class ReorderingTest2 {


    int a = 0;

    int i = 0;
    boolean flag = false;

    public void writer() {
        a = 1;
        flag = true;
    }

    public void reader() {
        if (flag) {
            i = a * a;
        }
    }

    @Test
    void testReordering() throws InterruptedException {
        while (true) {
            a = 0;
            i = 0;
            Thread thread = new Thread(this::writer);
            Thread thread1 = new Thread(this::reader);
            thread.start();
            thread1.start();
            thread.join();
            thread1.join();
            if (i == 0) {
                System.out.println("a: " + a + ", i: " + i);
                break;
            }
        }
    }
}
