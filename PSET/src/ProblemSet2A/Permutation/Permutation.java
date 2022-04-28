package ProblemSet2A.Permutation;


import java.util.ArrayList;

public class Permutation {
    private final String in;
    private ArrayList<String> a = new ArrayList<String>();
    // additional attribute if needed


    Permutation(final String str){
        in = str;
        // additional initialization if needed

    }

    public void permute(){
        // produce the permuted sequence of 'in' and store in 'a', recursively
        permutate("", this.in);
    }

    public void permutate(String str1, String str2) {
        if (str2.length() == 1) {
            a.add(0, str1 + str2);
        } else {
            for (int i = str2.length() - 1; i >= 0; i--) {
                permutate(str1 + str2.substring(i, i + 1), str2.substring(0, i) + str2.substring(i + 1));
            }
        }
    }

    public ArrayList<String> getA(){
        return a;
    }


//    def permutate(s):
//            return permutate_recursive("", s)
//    pass
//
//    def permutate_recursive(str1, str2):
//    result = []
//
//            if len(str2) == 1:
//            return [str1 + str2]
//
//            for i in range(-1,-(len(str2) + 1), -1):
//            # Start with str1 = "c", therefore to put cba at the back, result has to be at the back
//            result = permutate_recursive(str1 + str2[i], str2.replace(str2[i], "")) + result
//
//    return result
}

