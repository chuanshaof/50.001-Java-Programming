package ProblemSet1A.homework2_1;

public class LinearEquation {
    private double a, b, c, d, e, f;

    LinearEquation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public double getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }

    public double getC() {
        return this.c;
    }

    public double getD() {
        return this.d;
    }

    public double getE() {
        return this.e;
    }

    public double getF() {
        return this.f;
    }

    public boolean isSolvable() {
        if (this.a * this.d - this.b * this.c == 0) {
            return false;
        } else {
            return true;
        }
    }

    public double getX() {
        double dMultiplier = this.b / this.d;

        double multipliedC = this.c * dMultiplier;
        double multipliedF = this.f * dMultiplier;

        double reducedA = this.a - multipliedC;
        double reducedE = this.e - multipliedF;

        return reducedE / reducedA;
    }

    public double getY(){
        return (this.e - this.a * this.getX()) / this.b;
    }
}
