package Lab17;

public class EmployeeController {
    private Employee model;
    private EmployeeView view;

    public EmployeeController(Employee model, EmployeeView view){
        this.model=model;
        this.view=view;
    }
    public String getEmpName() {
        return model.getEmpName();
    }

    public int getExpiriance() {
        return model.getExpiriance();
    }

    public int getQualification() {
        return model.getQualification();
    }

    public int getHoursWorkingAtWeek() {
        return model.getHoursWorkingAtWeek();
    }

    public void setEmpName(String empName) {
        model.setEmpName(empName);
    }

    public void setExpiriance(int expiriance) {
        model.setExpiriance(expiriance);
    }

    public void setHoursWorkingAtWeek(int hoursWorkingAtWeek) {
        model.setHoursWorkingAtWeek(hoursWorkingAtWeek);
    }

    public void setQualification(int qualification) {
        model.setQualification(qualification);
    }

    public int calculateSalary(){
        int salary = model.getHoursWorkingAtWeek()*50000+model.getExpiriance()*10000+ model.getExpiriance()*15000;
        return salary;
    }

    public void update(){
        view.printInfo(model.getEmpName(), model.getHoursWorkingAtWeek(), model.getQualification(), model.getExpiriance(), calculateSalary());
    }
}