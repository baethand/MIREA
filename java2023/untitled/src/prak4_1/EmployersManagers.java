package prak4_1;

class Employer {
    private String firstName;
    private String lastName;
    private double income;

    public Employer(String firstName, String lastName, double income) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.income = income;
    }

    public double getIncome() {
        return income * 12;
    }
}

class Manager extends Employer {
    private double averageSum;

    public Manager(String firstName, String lastName, double income, double averageSum) {
        super(firstName, lastName, income);
        this.averageSum = averageSum;
    }

    @Override
    public double getIncome() {
        return super.getIncome() + averageSum;
    }
}

public class EmployersManagers {
    public static void main(String[] args) {
        Employer employer1 = new Employer("Иван", "Иванов", 50000);
        Manager manager1 = new Manager("Петр", "Петров", 60000, 5000);

        Employer[] employees = { employer1, manager1 };

        for (Employer employee : employees) {
            System.out.println(employee.getIncome());
        }
    }
}

