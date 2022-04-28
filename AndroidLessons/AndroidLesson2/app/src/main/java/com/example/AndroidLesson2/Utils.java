package com.example.AndroidLesson2;

public class Utils {

    static void checkInvalidInputs (String value) {
        Double decimal = Double.valueOf(value);
        if (decimal <= 0) {
            throw new IllegalArgumentException();
        }
    }

}
