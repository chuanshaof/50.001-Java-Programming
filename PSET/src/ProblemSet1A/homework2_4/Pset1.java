package ProblemSet1A.homework2_4;

// **ATTENTION**
// Edit just this file to submit your answer
// You need not edit the TestPset1.java file

import java.util.*;

public class Pset1 {
    public static boolean isAllCharacterUnique(String sIn) {
        if (sIn.length() > 128) {
            return false;
        }

        Set<Character> setChar = new HashSet<>();

        for (int i = 0; i < sIn.length(); i++) {
            setChar.add(sIn.charAt(i));
        }

        if (sIn.length() > setChar.size()) {
            return false;
        }

        return true;

    }
    public static boolean isPermutation(String sIn1, String sIn2) {
        if (sIn1.length() != sIn2.length()) {
            return false;
        }

        int[] arrayInt1 = new int[128];
        int[] arrayInt2 = new int[128];

        Arrays.fill(arrayInt1, 0);
        Arrays.fill(arrayInt2, 0);


        for (int i = 0; i < sIn1.length(); i++) {
            arrayInt1[(int) sIn1.charAt(i)]++;
            arrayInt2[(int) sIn2.charAt(i)]++;
        }

        return Arrays.equals(arrayInt1, arrayInt2);
    }
}

