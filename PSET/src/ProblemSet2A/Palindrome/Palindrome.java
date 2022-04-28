package ProblemSet2A.Palindrome;

import java.util.Arrays;

public class Palindrome {
    public static boolean isPalindrome (char[] S) {
        if (S.length == 2) {
            if (S[0] == S[1]) {
                return true;
            }
        } else if (S.length < 2) {
            return true;
        } else {
            if (S[0] == S[S.length - 1]) {
                char[] next = Arrays.copyOfRange(S, 1, S.length - 1);
                return isPalindrome(next) && true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[] s1 = {'a', 'b', 'b', 'a'};
        System.out.println(isPalindrome(s1));

        char[] s2 = {'a', 'd', 'b', 'c', 'b', 'a'};
        System.out.println(isPalindrome(s2));

        char[] s3 = {'Z', 'Z', 'a', 'Z', 'Z'};
        System.out.println(isPalindrome(s3));

        char[] s4 = {'1', '2', '3', '4', '2', '1'};
        System.out.println(isPalindrome(s4));
    }
}


/* ATTENTION
The method isPalindrome() returns true if the input string is a palindrome, and false otherwise.
It is NOT NECESSARY to do any System.out.println() of "abba is a palindrome" etc.
*/


