package randomstuff;

public class Question2 {

    public static void main(String[] args) {
        //outputs: 1 and 2
        System.out.println( distance( "kitten", "mitten") );
        System.out.println( distance( "kitten", "mitter") );
        System.out.println( distance( "", "tester"));
        System.out.println( distance( "tester", ""));
        System.out.println( min(3, 0, 2));
    }

    public static int distance( String a, String b) {
        if (b.length() == 0) {
            return a.length();
        }

        if (a.length() == 0) {
            return b.length();
        }

        String tailA = a.substring(1);
        String tailB = b.substring(1);

        if (a.charAt(0) == b.charAt(0)) {
            return distance(tailA, tailB);
        } else {
            return 1 + min(distance(tailA, b), distance(a, tailB), distance(tailA, tailB));
        }
    }

    public static int min(int first, int second, int third) {
        int minimum_mid = Math.min(first, second);
        return Math.min(minimum_mid, third);
    }
}

