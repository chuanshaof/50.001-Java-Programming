package ProblemSet1A.fibonacci;

// **ATTENTION**
// Edit just this file to submit your answer
// You need not edit the Main.java file

import java.util.ArrayList;

public class Fibonacci{

    public static String fibonacci( int n ){

        ArrayList<Integer> fib = new ArrayList<Integer>(){
            {
                add(0);
                add(1);
            }
        };

        String fibo = "0, 1";

        Integer newFib = 0;

        for (int i = 2; i < n; i++) {

            newFib = fib.get(0) + fib.get(1);
            fib.add(newFib);
            fib.remove(0);

            fibo = fibo + ", " + newFib.toString();
        }
        return fibo;
    }

}