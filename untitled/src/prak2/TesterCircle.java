package prak2;

import java.util.ArrayList;

public class TesterCircle {
    ArrayList<Circle> circles = new ArrayList<>();
    int size = circles.size();

    public void init(){
        circles.add(new Circle(2.346, 4.4573));
        updateSize();
    }

    public void updateSize(){
        size = circles.size();
    }
}
