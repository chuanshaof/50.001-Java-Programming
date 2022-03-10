package ProblemSet1A.iterator2;

// ATTENTION
// just edit this file
// you need not edit Main.java

import java.util.Iterator;
import java.util.List;

// ATTENTION
// just edit this file
// you need not edit Main.java


import java.util.*;

public class IteratingExamples {

    public static int Act2ForEach(List<Integer> integers) {
        int sum = 0;

        for (int i = 0; i < integers.size(); i ++) {
            sum += integers.get(i);
        }
        return sum;

    }
}

