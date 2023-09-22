package com.yczuoxin.myservice.support;

import org.junit.jupiter.api.Test;

class CalcTest {

    @Test
    void calc() {
        int single = 8;
        int sum = 0;
        int grade = 10;
        int start = 9;
        for (int i = 0; i < grade; i++) {
            if (i >= start) {
                sum += single;
            }
            single = single * 2;
        }
        System.out.println(sum);
    }

}
