package ProblemSet1A.homework1_2;

public class Main {
    public static void main(String[] args) {
        MyRectangle2D rDefault = new MyRectangle2D();
        MyRectangle2D rOne = new MyRectangle2D(1,1,6,6);
        MyRectangle2D rTwo = new MyRectangle2D(0, 0, 2, 2);
        MyRectangle2D rThree = new MyRectangle2D(-3, -3, 2, 2);
        MyRectangle2D rFour = new MyRectangle2D(-7, -7 , 2, 2);
        MyRectangle2D rFive = new MyRectangle2D(0, 0 , 10, 20);

        System.out.println(rFive.contains(9.9999999, 1));

    }
}
