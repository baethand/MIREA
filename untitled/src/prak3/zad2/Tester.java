package prak3.zad2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Tester {
    private Circle[] circles;
    private int count;

    public Circle[] getCircles() {
        return circles;
    }

    public void setCircles(Circle[] circles) {
        this.circles = circles;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public Tester(int size) {
        circles = new Circle[size];
        count = 0;
    }

    public void addCircle(Circle circle) {
        circles[count++] = circle;
    }

    public Circle getSmallestCircle() {
        Circle smallestCircle = circles[0];
        for (int i = 1; i < count; i++) {
            if (circles[i].getRadius() < smallestCircle.getRadius()) {
                smallestCircle = circles[i];
            }
        }
        return smallestCircle;
    }

    public Circle getLargestCircle() {
        Circle largestCircle = circles[0];
        for (int i = 1; i < count; i++) {
            if (circles[i].getRadius() > largestCircle.getRadius()) {
                largestCircle = circles[i];
            }
        }
        return largestCircle;
    }

    public void sortCirclesByRadius() {
        Arrays.sort(circles, 0, count, new Comparator<Circle>() {
            @Override
            public int compare(Circle circle1, Circle circle2) {
                return Double.compare(circle1.getRadius(), circle2.getRadius());
            }
        });
    }



}
