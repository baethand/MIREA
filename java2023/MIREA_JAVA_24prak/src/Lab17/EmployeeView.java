package Lab17;

public class EmployeeView {
    public static void printInfo(String name, int hours, int qual, int exp, int salary){
        System.out.println("Имя сотрудника: "+name);
        System.out.println("Работает "+hours + "часов в неделю");
        System.out.println("Имеет квалификацию: "+qual);
        System.out.println("Имеет опыт: "+exp);
        System.out.println("Зарабатывает: " + salary + "руб");
    }
}