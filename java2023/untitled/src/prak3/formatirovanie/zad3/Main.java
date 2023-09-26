package prak3.formatirovanie.zad3;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Иванов Иван Иванович", 50000.0);
        employees[1] = new Employee("Петров Петр Петрович", 75000.0);
        employees[2] = new Employee("Сидоров Сидор Сидорович", 90000.0);

        Report.generateReport(employees);
    }
}
