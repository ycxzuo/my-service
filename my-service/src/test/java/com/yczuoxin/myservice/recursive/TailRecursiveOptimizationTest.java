package com.yczuoxin.myservice.recursive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;

class TailRecursiveOptimizationTest {

    @Test
    void testTailRecursive() {
        long n = 9000L;
        long start1 = System.currentTimeMillis();
        long result1 = factorialRecursive(n);
        System.out.println(System.currentTimeMillis() - start1);
        long start2 = System.currentTimeMillis();
        long result2 = factorialStreams(n);
        System.out.println(System.currentTimeMillis() - start2);
        long start3 = System.currentTimeMillis();
        long result3 = factorialTailRecursive(n);
        System.out.println(System.currentTimeMillis() - start3);
        Assertions.assertEquals(result1, result2);
        Assertions.assertEquals(result1, result3);
    }

    /**
     * 简单的递归方式
     *
     * @param n
     * @return
     */
    private long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }

    /**
     * Stream 流的方式
     *
     * @param n
     * @return
     */
    private long factorialStreams(long n) {
        return LongStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

    private long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    /**
     * 递归调用发生在方法的最后,这种形式的递归是非常有意义的，现在我们不需要在不同的栈帧上保存每次递归计算的中间值，
     * 编译器能够自行决定复用某个栈帧进行计算。但是目前 Java 还不支持这一种优化
     *
     * @param n
     * @param acc
     * @return
     */
    private long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n -1);
    }

}
