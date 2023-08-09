package com.yczuoxin.myservice.others.override;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SubmitFunctionTest {

    @Test
    void testSubmit() {
        new Thread(System.out::println).start();

        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(System.out::println); // 匹配到无参方法和带一个boolean类型的方法
        executorService.submit(() -> System.out.println());
    }

}
