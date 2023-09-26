package prak4_1.zad8;

import static java.lang.Math.pow;

public class zad8 {
}

class Rectangle extends Shape{

    protected double width;
    protected double length;

    public Rectangle(String color, boolean filled, double width, double length) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public Rectangle() { }

    @Override
    public double getArea() {
        return width*length;
    }

    @Override
    public double getPerimeter() {
        return 2*(width*length);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}

class Square extends Rectangle{

    public Square(String color, boolean filled, double side) {
        super(color, filled, side,side);
    }

    public Square(double side) {
        super(side, side);
    }

    public Square() {
    }

    public  double getSide(){
        return super.getLength();
    }

    public void setSide(double side){
        super.setLength(side);
    }

    public void setWidth(double side) {
        super.setWidth(side);
    }
    public void setLength(double side) {
        super.setLength(side);
    }

    @Override
    public String toString() {
        return "Square{" +
                "width=" + width +
                ", length=" + length +
                ", color='" + color + '\'' +
                ", filled=" + filled +
                '}';
    }
}


