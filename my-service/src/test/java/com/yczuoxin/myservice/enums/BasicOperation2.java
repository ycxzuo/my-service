package com.yczuoxin.myservice.enums;

import java.util.function.DoubleBinaryOperator;

public enum BasicOperation2 {

    PLUS("+", Double::sum),
    MINUS("-", (x, y) -> x - y),
    DIVIDE("/", (x, y) -> x / y),
    TIMES("*", (x, y) -> x * y);

    private final String symbol;

    private final DoubleBinaryOperator operator;

    BasicOperation2(String symbol, DoubleBinaryOperator operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    public double apply(double a, double b) {
        return operator.applyAsDouble(a, b);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
