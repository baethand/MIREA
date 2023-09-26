package prak2;

import static java.lang.Math.pow;

public class Circle {
    private Point center;
    private double radius;

    public boolean isEquals(Circle circle){
        if (this.center.getX() == circle.getCenter().getX()
                && this.center.getY() == circle.getCenter().getY()
                && this.radius == circle.getRadius()){
            return true;
        }
        return false;
    }

    public Circle(double x, double y, double radius) {
        this.center = new Point();
        this.center.setX(x);
        this.center.setY(y);
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
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



    public void setXY(double x, double y) {
        this.center.setX(x);
        this.center.setY(y);
    }
}
