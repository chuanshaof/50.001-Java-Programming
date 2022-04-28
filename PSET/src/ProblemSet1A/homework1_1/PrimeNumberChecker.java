package ProblemSet1A.homework1_1;

public class PrimeNumberChecker {
    public static int isPrime(int num){
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0){
                return 0;
            }
        }
        return 1;
    }
}
