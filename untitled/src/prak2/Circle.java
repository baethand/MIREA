package prak2;

import static java.lang.Math.pow;

public class Circle {
    private double x;
    private double y;
    private double radius;

    public boolean isEquals(Circle circle){
        if (this.x == circle.getX()
                && this.y == circle.getY()
                && this.radius == circle.getRadius()){
            return true;
        }
        return false;
    }

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getS(double radius){
        return 3.1415 * pow(radius,2);
    }

    public double getP(double radius){
        return radius * 3.1415;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
