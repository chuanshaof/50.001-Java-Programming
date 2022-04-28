package com.example.AndroidLesson2;

import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class ExchangeRate {

    private BigDecimal exchangeRate;
    private static String defaultRate = "2.95000";
    private static final int DEFAULT_PRECISION = 5;
    private int precision = DEFAULT_PRECISION;
    private MathContext mathContext;


    ExchangeRate(){
        exchangeRate = new BigDecimal(defaultRate);
        instantiateMathContext(DEFAULT_PRECISION);
    }

    ExchangeRate(String exchangeRate){
        this.exchangeRate = new BigDecimal(exchangeRate);
        instantiateMathContext(DEFAULT_PRECISION);
    }

    ExchangeRate(String home, String foreign) {
        instantiateMathContext(DEFAULT_PRECISION);
        Utils.checkInvalidInputs(home);
        Utils.checkInvalidInputs(foreign);

        //TODO 3.13a The constructor initializes exchangeRate by calculating the exchangeRate
        BigDecimal local = new BigDecimal(home).setScale(2, RoundingMode.HALF_UP);
        BigDecimal overseas = new BigDecimal(foreign);
        exchangeRate = overseas.divide(local, mathContext);
    }

    BigDecimal getExchangeRate(){
        return exchangeRate;
    }

    BigDecimal calculateAmount(String foreign){
        //TODO 2.5a complete this method to return the amount
        BigDecimal value = new BigDecimal(foreign);
        return value.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
    }

    void setPrecision(int precision){
        this.precision = precision;
        instantiateMathContext(precision);
    }

    private void instantiateMathContext(int precision){
        mathContext = new MathContext(precision, RoundingMode.HALF_UP);
    }
}
