package com.example.AndroidLesson2;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void exchangeRateDefaultRate() {
        String defaultExchangeRate = "2.95000";
        assertEquals(new BigDecimal(defaultExchangeRate), new ExchangeRate().getExchangeRate());
    }

    @Test
    public void exchangeRateExchangeRate() {
        String setExchangeRate = "3.0000";
        assertEquals(new BigDecimal(setExchangeRate), new ExchangeRate(setExchangeRate).getExchangeRate());
    }

    @Test
    public void exchangeRateHomeForeign() {
        String home = "2.1652316";
        String foreign = "1.3213124";
        String expectedRate = "0.60890";
        assertEquals(new BigDecimal(expectedRate), new ExchangeRate(home, foreign).getExchangeRate());
    }

    @Test(expected = NumberFormatException.class)
    public void emptyExchangeRate() {
        String home = "s";
        String foreign = "3.1415926535";
        new ExchangeRate(home, foreign).getExchangeRate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidExchangeRate() {
        String home = "0";
        String foreign = "-2";
        new ExchangeRate(home, foreign).getExchangeRate();
    }

    @Test
    public void calculateAmount() {
        String setExchangeRate = "3.0000";
        ExchangeRate exchangeRate = new ExchangeRate(setExchangeRate);

        String value = "200";
        String expectedValue = "600.00";

        assertEquals(new BigDecimal(expectedValue), exchangeRate.calculateAmount(value));
    }
}