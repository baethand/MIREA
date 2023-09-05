package prak1;

public class Ball {
    private double x;
    private double y;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(double xDisp, double yDisp){
        x+=xDisp;
        y+=yDisp;
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

    @Override
    public String toString() {
        return "prak1.prak1.Ball{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
