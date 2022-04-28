package ProblemSet1B.piwords;

public class BaseTranslator {
    /**
     * Converts an array where the ith digit corresponds to (1 / baseA)^(i + 1)
     * digits[i], return an array output of size precisionB where the ith digit
     * corresponds to (1 / baseB)^(i + 1) * output[i].
     * 
     * Stated in another way, digits is the fractional part of a number
     * expressed in baseA with the most significant digit first. The output is
     * the same number expressed in baseB with the most significant digit first.
     * 
     * To implement, logically, you're repeatedly multiplying the number by
     * baseB and chopping off the most significant digit at each iteration:
     * 
     * for (i < precisionB) {
     *   1. Keep a carry, initialize to 0.
     *   2. From RIGHT to LEFT
     *   	a. x = multiply the jth digit by baseB and add the carry
     *          b. the new jth digit is x % baseA
     *          c. carry = x / baseA
     *   3. output[i] = carry
     * }
     * If digits[j] < 0 or digits[j] >= baseA for any j, return null
     * If baseA < 2, baseB < 2, or precisionB < 1, return null
     * 
     * @param digits The input array to translate. This array is not mutated.
     * @param baseA The base that the input array is expressed in.
     * @param baseB The base to translate into.
     * @param precisionB The number of digits of precision the output should
     *                   have.
     * @return An array of size precisionB expressing digits in baseB.
     */
    public static int[] convertBase(int[] digits, int baseA, int baseB, int precisionB) {
        if (baseA <= 1 || baseB <= 1 || precisionB < 1) {
            return null;
        }

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] < 0 || digits[i] >= baseA) {
                return null;
            }
        }

        int[] out = new int[precisionB];

        for (int i = 0; i < precisionB; i++) {
            int next = 0;

            // Start from the back of the digits
            for (int j = digits.length - 1; j>=0; j--) {
                long num = (baseB * digits[j]) + next;
                next = (int) (num / baseA);
                digits[j] = (int) (num % baseA);
            }
            out[i] = next;
        }
        return out;

        // Issue with the bottom code is to use BigDecimal

//        double base10Digits = 0;
//
//        for (int i = 0; i < digits.length; i++) {
//            base10Digits = base10Digits + digits[i] * Math.pow(baseA, -i - 1);
//        }
//
//        int[] out = new int[precisionB];
//
//        for (int i = 0; i < precisionB; i++) {
//            out[i] = (int) (base10Digits / Math.pow(baseB, -i - 1));
//            base10Digits = base10Digits - (out[i] * Math.pow(baseB, -i - 1));
//        }
//        return out;
    }
}
