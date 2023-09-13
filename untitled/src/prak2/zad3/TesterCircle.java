package prak2.zad3;

import prak2.Circle;

import java.util.ArrayList;

public class TesterCircle {
    ArrayList<Circle> circles = new ArrayList<>();
    int size = circles.size();

    public void init(){
        circles.add(new Circle(2.346, 4.4573, 1.345));
        updateSize();
    }

    public void updateSize(){
        size = circles.size();
    }
}
