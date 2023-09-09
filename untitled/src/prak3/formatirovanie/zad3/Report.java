package prak3.formatirovanie.zad3;

public class Report {
    public static void generateReport(Employee[] employees) {
        System.out.println("Отчет о зарплате сотрудников:");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s %10s%n", "ФИО сотрудника", "Зарплата");
        System.out.println("--------------------------------------------------");
        for (Employee employee : employees) {
            System.out.printf("%-20s %,10.2f%n", employee.getFullName(), employee.getSalary());
        }
        System.out.println("--------------------------------------------------");
    }
}
