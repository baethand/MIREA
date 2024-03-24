package Lab7;

interface Movable {
    void up();
    void down();
    void left();
    void right();
}


class MovableRectangle implements Movable {
    private int x;
    private int y;
    private int width;
    private int length;

    public MovableRectangle(int x, int y, int w, int l) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.length = l;
    }

    public void ToString(){
        System.out.println("Длина прямоугольника: "+this.length);
        System.out.println("Ширина прямоугольника: "+this.width);
        System.out.println("Координаты прямоугольника прямоугольника: "+this.x+", "+this.y);
    }

    @Override
    public void up() {
        this.y++;
    }

    @Override
    public void down() {
        this.y--;
    }

    @Override
    public void left() {
        this.x--;
    }

    @Override
    public void right() {
        this.x++;
    }

    public static void main(String[] args) {
        MovableRectangle rect1 = new MovableRectangle(0, 0, 20, 25);
        rect1.ToString();
        rect1.up();
        rect1.left();
        rect1.ToString();
    }
}