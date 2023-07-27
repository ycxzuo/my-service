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

    @Test
    void testOperation() {
        test(BasicOperation.class, 2, 1);
    }

    @Test
    void testOperation2() {
        double x = 2;
        double y = 1;
        for (BasicOperation2 basicOperation2 : BasicOperation2.values()) {
            System.out.printf("%f %s %f = %f%n",  x, basicOperation2, y, basicOperation2.apply(x, y));
        }
    }


    private <T extends Enum<T> & Operation> void test(Class<T> operationType, double x, double y) {
        for (Operation operation : operationType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, operation, y, operation.apply(x, y));
        }

    }

}
