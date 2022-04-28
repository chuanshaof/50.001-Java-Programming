package ProblemSet1A.fibonacci;

// **ATTENTION**
// YOU DO NOT NEED TO EDIT THIS FILE

public class Main {

    public static void main(String[] args) {

        int n;
        n = Integer.parseInt(args[0]);
        String ans = "";

        Fibonacci fibo = new Fibonacci();
        ans = fibo.fibonacci(n);

        System.out.println(ans);
    }
}