package Lab10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class SortingStudentsByGPA {
    private ArrayList<Student> iDNumber;

    public void addStudents(int n) {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Enter First name");
            String fN = in.nextLine();
            System.out.println("Enter Second name");
            String sN = in.nextLine();
            System.out.println("Enter Group");
            String group = in.nextLine();
            System.out.println("Enter Spec");
            String spec = in.nextLine();
            System.out.println("Enter Course");
            int course = in.nextInt();
            System.out.println("Enter Score");
            double score = in.nextInt();
            Student s = new Student(fN, sN, spec, course, group, score);
            iDNumber.add(s);
        }
    }

    public void outArray() {
        for (Student student : iDNumber) {
            System.out.println(student.getFirstName() + " " + student.getSecondName() + " Group: " + student.getGroup() + " Spec:" + student.getSpec() + " Course: " + student.getCourse());
        }
    }

    public void quickSort() {
        iDNumber.sort(Comparator.comparingDouble(Student::getScore).reversed());
    }

    private void mergeSort(ArrayList<Student> list, int left, int right, Comparator<Student> comparator) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(list, left, middle, comparator);
            mergeSort(list, middle + 1, right, comparator);
            merge(list, left, middle, right, comparator);
        }
    }

    private void merge(ArrayList<Student> list, int left, int middle, int right, Comparator<Student> comparator) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        ArrayList<Student> leftList = new ArrayList<>();
        ArrayList<Student> rightList = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            leftList.add(list.get(left + i));
        }

        for (int i = 0; i < n2; i++) {
            rightList.add(list.get(middle + 1 + i));
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) >= 0) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public void sortByFirstName() {
        iDNumber.sort(Comparator.comparing(Student::getFirstName));
    }
}