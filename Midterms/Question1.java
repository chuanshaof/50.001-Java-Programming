package randomstuff;

import java.util.ArrayList;

public class Question1 {

    public static void main(String[] args) {

        //output: 25
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(sumThreeAndSeven(values));

        // output: shnydys
        System.out.println( dropVowelsAndCase("SHINYDays")); //shnydys

        // output: [3, 0, 2, 2, 4, 6, 10]
        System.out.println(someSequence(6));
        // output: [3, 0, 2, 2]
        System.out.println(someSequence(3));
    }

    public static int sumThreeAndSeven( int[] values){
        int sum = 0;
        for (int i : values) {
            if ((i % 3) == 0){
                sum = sum + i;
            } else if ((i % 7) == 0) {
                sum = sum + i;
            }
        }
        return sum;
    }

    public static String dropVowelsAndCase(String s){
        String string = "";
        String lowerS = s.toLowerCase();

        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for (int i = 0; i < s.length(); i++) {
            if (!vowels.contains(lowerS.charAt(i))) {
                string = string + lowerS.charAt(i);
            }
        }
        return string;
    }

    public static ArrayList<Integer> someSequence(int n){
        ArrayList<Integer> sequence = new ArrayList<>();

        sequence.add(3);

        if (n == 0) {
            return sequence;
        }

        sequence.add(0);

        if (n == 1) {
            return sequence;
        }
        
        sequence.add(2);

        if (n < 3) {
            return sequence;
        }

        for (int i = 3; i <= n; i++) {
            sequence.add(sequence.get(i - 2) + sequence.get(i - 1));
        }

        return sequence;
    }
}

