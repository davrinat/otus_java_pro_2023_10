package ru.otus.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculate {
    public BigDecimal multiply(double x, double y) {
        return BigDecimal.valueOf(x * y).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal divide(double x, double y) {
        return BigDecimal.valueOf(x / y).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal sum(double x, double y) {
        return BigDecimal.valueOf(x + y).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal minus(double x, double y) {
        return BigDecimal.valueOf(x - y).setScale(2, RoundingMode.HALF_EVEN);
    }
}
