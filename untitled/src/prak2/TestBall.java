package prak2;

public class TestBall {
    public static void main(String[] args){
        Ball ball = new Ball(1, 2);
        System.out.println(ball.toString());
        ball.move(2, 3);
        System.out.println(ball.toString());
    }
}

class Ball {
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
        return "prak2.Ball{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
