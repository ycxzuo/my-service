package com.yczuoxin.myservice.others.jmh;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime) // 测量用于执行基准测试目标方法所花费的平均时间
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 以毫秒为单位，打印输出基准测试的结果
//@Warmup(iterations = 3) // 预热
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"}) // 采用 4G 的堆，执行基准测试两次以获得更可靠的结果
@Slf4j
@State(Scope.Thread)// 必须与 @TearDown 同时使用
public class ParallelStreamBenchmark {

    private static final long N = 10_000_000L;

    @Benchmark // 基准测试的目标方法
    public long sequentialSum() {
        return Stream.iterate(1L, i -> i + 1).limit(N).reduce(0L, Long::sum);
    }

    @Benchmark // 基准测试的目标方法
    public long sequentialSum2() {
        return Stream.iterate(1L, i -> i + 1).parallel().limit(N).reduce(0L, Long::sum);
    }

    @TearDown(Level.Invocation) // 尽量在每次基准测试迭代结束后都进行一次垃圾回收
    public void tearDown() {
        System.gc();
    }

    @Benchmark
    public long rangedSum() {
        return LongStream.rangeClosed(1, N).reduce(0L, Long::sum);
    }

    @Benchmark
    public long rangedSum2() {
        return LongStream.rangeClosed(1, N).parallel().reduce(0L, Long::sum);
    }

}
