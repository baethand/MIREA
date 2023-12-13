package Lab19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LabClassUI {
    public static void run(){
        LabClass labClass = new LabClass();
        Scanner in = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Введите номер желаемого действия: ");
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить студентов");
            System.out.println("2. Найти студента по имени и фамилии");
            System.out.println("3. Вывести список всех студентов");
            System.out.println("4. Сортировать студентов по среднему баллу");
            System.out.println("5. Сортировать студентов по имени");
            System.out.println("6. Закончить");
            choice = in.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter First name");
                    String fN = in.next();
                    System.out.println("Enter Second name");
                    String sN = in.next();
                    System.out.println("Enter Group");
                    String group = in.next();
                    System.out.println("Enter Spec");
                    String spec = in.next();
                    System.out.println("Enter Course");
                    int course = in.nextInt();
                    System.out.println("Enter Score");
                    double score = in.nextDouble();
                    try {
                        Students s = new Students(fN, sN, spec, course, group, score);
                        labClass.addStudent(s);
                        System.out.println("Студент успешно добавлен");
                    }catch (EmptyStringException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Введите имя студента");
                    String searchFirstName = in.next();
                    System.out.println("Введите фамилию студента");
                    String searchSecondName = in.next();
                    try{
                        Students s = labClass.findStudent(searchFirstName, searchSecondName);
                        s.getInfo();
                    }catch (StudentNotFoundException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    ArrayList<Students> students = labClass.getAllStudents();
                    System.out.println("Список всех студентов:");
                    for (Students student : students) {
                        student.getInfo();
                    }
                    break;
                case 4:
                    labClass.quickSort();
                    break;
                case 5:
                    labClass.sortByFirstName();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Введите цифру от 1 до 6");
            }
        }while (true);
    }
}