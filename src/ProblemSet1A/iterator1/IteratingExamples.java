package ProblemSet1A.iterator1;

// ATTENTION
// just edit this file
// you need not edit Main.java

import java.util.*;

public class IteratingExamples {

    public static int Act2Iterator(List<Integer> integers) {
        int sum = 0;

        // Insert code here to sum up input using an Iterator ...

        Iterator i = integers.iterator();

        while(i.hasNext()) {
            sum += Integer.parseInt(i.next().toString());
        }

        return sum;
    }
}
