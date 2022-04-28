package ProblemSet1A.homework2_2;

// **ATTENTION**
// Edit just this file to submit your answer
// You need not edit TestTriangle.java
//
// GeometricObject.class:
// -- This is a Java bytecode file
// -- just leave it alone, you should not click on it and type in it
// -- Reset the assignment (Actions --> Reset Assignment) if you encounter issues after clicking this file

class Triangle extends GeometricObject {
    private double side1 = 1, side2 =1, side3 = 1;

    Triangle() {
    }

    Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getArea() {
        double s = (this.side1 + this.side2 + this.side3) / 2;
        return Math.sqrt(s * (s - this.side1) * (s - this.side2) * (s - this.side3));
    }

    public double getPerimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public String toString() {
        return "Triangle: side1 = " + Double.toString(this.side1) + " side2 = "
                + Double.toString(this.side2) + " side3 = " + Double.toString(this.side3);
    }
}

