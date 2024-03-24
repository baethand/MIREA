package Lab24;

public class Complex {
    private int real;
    private int imaginary;

    public Complex() {
        this.real = 0;
        this.imaginary = 0;
    }

    public Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Геттеры и сеттеры для действительной и мнимой части

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }
}
