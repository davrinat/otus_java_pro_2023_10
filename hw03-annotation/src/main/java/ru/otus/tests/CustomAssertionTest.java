package ru.otus.tests;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.assertions.CustomAssert;
import ru.otus.services.Calculate;

import java.math.BigDecimal;

public class CustomAssertionTest {
    private static Calculate calculate;
    private static CustomAssert customAssert;

    @Before
    public void before() {
        calculate = new Calculate();
        customAssert = new CustomAssert();
        customAssert.init();
    }

    @Test
    public void testSum() {
        var actual = calculate.sum(2.5, 3.5);
        var expected = new BigDecimal("6.00");
        customAssert.customAssertThis(expected, actual);
    }

    @Test
    public void testMultiply2() {
        var actual = calculate.multiply(4.5, 3.5);
        var expected = new BigDecimal("15.74");
        customAssert.customAssertThis(expected, actual);
    }

    @Test
    public void testMinus() {
        var actual = calculate.minus(4.5, 3.5);
        var expected = new BigDecimal("1.00");
        customAssert.customAssertThis(expected, actual);
    }

    @Test
    public void testDivide() {
        var actual = calculate.divide(12.5, 3.5);
        var expected = new BigDecimal("3.57");
        customAssert.customAssertThis(expected, actual);
    }

    @Test
    public void testMultiply1() {
        var actual = calculate.multiply(4.5, 3.5);
        var expected = new BigDecimal("15.75");
        customAssert.customAssertThis(expected, actual);
    }

    @After
    public void after() {
        calculate = null;
        customAssert.destroy();
    }
}
