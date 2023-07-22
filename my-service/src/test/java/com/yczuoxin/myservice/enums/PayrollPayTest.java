package com.yczuoxin.myservice.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

class PayrollPayTest {

    @Test
    void testPay() {
        System.out.println(PayrollDay.FRIDAY.pay(11 * 60, 2));
        System.out.println(PayrollDay.SUNDAY.pay(11 * 60, 2));
        Assertions.assertNotEquals(PayrollDay.FRIDAY.pay(11 * 60, 2), PayrollDay.SUNDAY.pay(11 * 60, 2));
        EnumSet<PayrollDay> weekend = EnumSet.of(PayrollDay.SATURDAY, PayrollDay.SUNDAY);
        Assertions.assertFalse(weekend.contains(PayrollDay.FRIDAY));
    }

}
