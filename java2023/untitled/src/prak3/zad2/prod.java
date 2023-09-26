package prak3.zad2;

import java.util.Random;

public class prod {

    public static void main(String[] args) {
        Tester tester = new Tester(5);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            double radius = random.nextDouble() * 5;
            Point center = new Point(x, y);
            Circle circle = new Circle(center, radius);
            tester.addCircle(circle);
        }

        Circle smallestCircle = tester.getSmallestCircle();
        Circle largestCircle = tester.getLargestCircle();

        System.out.println("Smallest circle: " + smallestCircle.getRadius());
        System.out.println("Largest circle: " + largestCircle.getRadius());

        tester.sortCirclesByRadius();
        for (int i = 0; i < 5; i++) {
            System.out.println("Circle " + (i+1) + ": " + tester.getCircles()[i].getRadius());
        }
    }
}
