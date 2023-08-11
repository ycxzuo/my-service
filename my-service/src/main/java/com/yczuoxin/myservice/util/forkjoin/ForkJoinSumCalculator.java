package com.yczuoxin.myservice.util.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;

    private final int start;

    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();// 小于阈值直接计算
        }
        ForkJoinSumCalculator left = new ForkJoinSumCalculator(numbers, start, start + length /2); // 创建子任务来为数组的前一半求和
        left.fork(); // 利用 ForkJoinPool 的另一个线程异步的执行新创建的子任务
        ForkJoinSumCalculator right = new ForkJoinSumCalculator(numbers, start + length / 2, end); // 创建子任务来为数组的后一半求和
        Long rightResult = right.compute(); // 同步执行第二个子任务，有可能进行进一步的递归划分
        Long leftResult = left.join(); // 读取第一个子任务的结果，如果尚未完成就等待
        return leftResult + rightResult; //  整合两个子任务的结果
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
