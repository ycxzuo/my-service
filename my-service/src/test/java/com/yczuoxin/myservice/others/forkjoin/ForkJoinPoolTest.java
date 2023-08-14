package com.yczuoxin.myservice.others.forkjoin;

import com.yczuoxin.myservice.util.forkjoin.ForkJoinSumCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

class ForkJoinPoolTest {

    @Test
    void forkJoinSum() {
        long[] numbers = LongStream.rangeClosed(1, 100_000).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        Long result = new ForkJoinPool().invoke(task);
        Assertions.assertEquals(LongStream.rangeClosed(1, 100_000).sum(), result);
    }

}
