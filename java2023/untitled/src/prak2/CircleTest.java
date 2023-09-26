package prak2;

public class CircleTest {

    public static void main(String[] args){
        Circle circle1 = new Circle(3,4,1);
        Circle circle2 = new Circle(5, 6, 2);
        System.out.println(circle1.isEquals(circle2));

        Circle circle3 = new Circle(3,4,1);
        Circle circle4 = new Circle(3, 4, 1);
        System.out.println(circle3.isEquals(circle4));
    }
}
