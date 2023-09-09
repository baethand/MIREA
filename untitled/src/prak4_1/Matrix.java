package prak4_1;

public class Matrix {
    private double[][] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    public Matrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = data;
    }

    public void add(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            System.out.println("Невозможно выполнить сложение. Размеры матриц не совпадают.");
            return;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] += other.data[i][j];
            }
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] *= scalar;
            }
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] data1 = { { 1.0, 2.0 }, { 3.0, 4.0 } };
        double[][] data2 = { { 0.5, 0.5 }, { 0.5, 0.5 } };

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);

        System.out.println("Матрица 1:");
        matrix1.print();

        System.out.println("\nМатрица 2:");
        matrix2.print();

        matrix1.add(matrix2);

        System.out.println("\nРезультат сложения матриц:");
        matrix1.print();

        matrix1.multiplyByScalar(2.0);

        System.out.println("\nРезультат умножения на число:");
        matrix1.print();
    }
}
