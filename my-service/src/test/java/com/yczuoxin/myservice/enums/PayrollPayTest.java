package com.yczuoxin.myservice.enums;

import org.junit.jupiter.api.Test;

class PayrollPayTest {

    @Test
    void testPay() {
        System.out.println(PayrollDay.FRIDAY.pay(11 * 60, 2));
        System.out.println(PayrollDay.SUNDAY.pay(11 * 60, 2));
    }

}
