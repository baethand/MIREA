package Lab17;

public class Employee {
    private String empName;
    private int hoursWorkingAtWeek;
    private int qualification;

    private int expiriance;

    public Employee(String empName, int hours, int qualification, int expiriance){
        this.empName = empName;
        this.hoursWorkingAtWeek = hours;
        this.qualification = qualification;
        this.expiriance = expiriance;
    }

    public String getEmpName() {
        return empName;
    }

    public int getExpiriance() {
        return expiriance;
    }

    public int getQualification() {
        return qualification;
    }

    public int getHoursWorkingAtWeek() {
        return hoursWorkingAtWeek;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setExpiriance(int expiriance) {
        this.expiriance = expiriance;
    }

    public void setHoursWorkingAtWeek(int hoursWorkingAtWeek) {
        this.hoursWorkingAtWeek = hoursWorkingAtWeek;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }
}