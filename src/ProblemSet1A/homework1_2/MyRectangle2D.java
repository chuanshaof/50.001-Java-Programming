package ProblemSet1A.homework1_2;

public class MyRectangle2D {
    private double x;
    private double y;
    private double width;
    private double height;

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setHeight(double height) {
        this.height = Math.abs(height);
    }

    public void setWidth(double width) {
        this.width = Math.abs(width);
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public MyRectangle2D() {
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.height = 1;
    }

    public MyRectangle2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

//    The below code does not work for some reason (on Vocareum)

//    public MyRectangle2D() {
//        this.setHeight(1);
//        this.setWidth(1);
//        this.setX(0);
//        this.setY(0);
//    }
//
//    public MyRectangle2D(double x, double y, double height, double width) {
//        this.setX(x);
//        this.setY(y);
//        this.setHeight(height);
//        this.setWidth(width);
//    }

    public double getArea() {
        return this.height * this.width;
    }

    public double getPerimeter() {
        return 2 * this.height + 2 * this.width;
    }

    public boolean contains(double x, double y) {
        double xDist = Math.abs(x - this.x);
        double yDist = Math.abs(y - this.y);

        if (xDist < this.width / 2 && yDist < this.height /2 ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(MyRectangle2D r) {
        double rLeftEdge = r.getX() - r.getWidth()/2;
        double rRightEdge = r.getX() + r.getWidth()/2;
        double rTopEdge = r.getY() + r.getHeight()/2;
        double rBottomEdge = r.getY() - r.getHeight()/2;

        double tLeftEdge = this.x - this.width/2;
        double tRightEdge = this.x + this.width/2;
        double tTopEdge = this.y + this.height/2;
        double tBottomEdge = this.y - this.height/2;

        if (tLeftEdge < rLeftEdge && tRightEdge > rRightEdge && tBottomEdge <
                rBottomEdge && tTopEdge > rTopEdge) {
            return true;
        } else {
            return false;
        }
    }

    public boolean overlaps(MyRectangle2D r) {
        double rLeftEdge = r.getX() - r.getWidth()/2;
        double rRightEdge = r.getX() + r.getWidth()/2;
        double rTopEdge = r.getY() + r.getHeight()/2;
        double rBottomEdge = r.getY() - r.getHeight()/2;

        double tLeftEdge = this.x - this.width/2;
        double tRightEdge = this.x + this.width/2;
        double tTopEdge = this.y + this.height/2;
        double tBottomEdge = this.y - this.height/2;

        boolean horizontal;
        boolean vertical;

        if ((tLeftEdge < rRightEdge && rRightEdge < tRightEdge) ||
                (tLeftEdge < rLeftEdge && rLeftEdge < tRightEdge)) {
            horizontal = true;
        } else {
            horizontal = false;
        }


        if ((tBottomEdge < rBottomEdge && rBottomEdge < tTopEdge) ||
                (tBottomEdge < rTopEdge && rTopEdge < tTopEdge)) {
            vertical = true;
        } else {
            vertical = false;
        }


        if (vertical == true && horizontal == true) {
            return true;
        } else {
            return false;
        }
    }
}
