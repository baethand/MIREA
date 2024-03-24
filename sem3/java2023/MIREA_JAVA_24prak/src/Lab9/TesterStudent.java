package Lab9;

public class TesterStudent {
    public static void main(String[] args) {
        Student[] students = {
                new Student(3, "Ваня"),
                new Student(1, "Петя"),
                new Student(4, "Гриша"),
                new Student(2, "Джони")
        };
        System.out.println(printID(students));

        selectionSort(students);

        System.out.println(printID(students));
    }

    private static String printID(Student[] array) {
        System.out.println("Массив id студентов");
        String s = "";
        for (Student student : array) {
            s += student.getId() + " ";
        }
        return s;
    }

    public static void selectionSort (Student[] arr){
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key)> 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}