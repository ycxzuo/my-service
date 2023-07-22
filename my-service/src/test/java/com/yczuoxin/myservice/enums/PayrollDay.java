package com.yczuoxin.myservice.enums;

public enum PayrollDay {

    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND),
    ;

    static final int MINS_PER_SHIFT = 60 * 8;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    PayrollDay() {
        this.payType = PayType.WEEKDAY;
    }

    private final PayType payType;

    public int pay(int mins, int payRate) {
        return payType.overtimePay(mins, payRate);
    }

    /**
     * 策略枚举使用
     */
    private enum PayType {
        WEEKDAY {
            @Override
            int overtimePay(int mins, int payRate) {
                return mins <= MINS_PER_SHIFT ? 0 : (mins - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            @Override
            int overtimePay(int mins, int payRate) {
                return mins * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);
    }
}
