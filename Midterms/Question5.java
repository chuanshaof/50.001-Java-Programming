package randomstuff;

public class Question5 {
    public static void main(String[] args) {
        A s = new D();
        s.p("1");
    }
}

class A {
    void p(String x) {
        System.out.println("P");
    }
}

class B extends A {
    void p(int x) {
        System.out.println("Q");
    }
}

class C extends A {
    @Override
    void p(String x) {
        System.out.println("R");
    }
}

class D extends C {

}