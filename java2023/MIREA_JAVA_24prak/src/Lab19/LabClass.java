package Lab19;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class LabClass {
    private ArrayList<Students> students;

    public LabClass(){
        students = new ArrayList<>();
    }
    public void addStudent(Students newStud) throws EmptyStringException {
        if(newStud.getFirstName().isEmpty()){
            throw new EmptyStringException("Имя не может быть пустым");
        } else if (newStud.getSecondName().isEmpty()) {
            throw new EmptyStringException("Фамилия не может быть пустой");
        } else if (newStud.getSpec().isEmpty()) {
            throw new EmptyStringException("Специальность не может быть пустой");
        } else if (newStud.getGroup().isEmpty()) {
            throw new EmptyStringException("Группа не может быть пустой");
        }else{
            students.add(newStud);
        }
    }

    public Students findStudent(String fN, String sN) throws StudentNotFoundException {
        for(Students s : students){
            if(fN.equals(s.getFirstName()) && sN.equals(s.getSecondName())){
                return s;
            }
        }
        throw new StudentNotFoundException("Студент не найден");
    }

    public ArrayList<Students> getAllStudents() {
        return students;
    }
    public void quickSort() {
        students.sort(Comparator.comparingDouble(Students::getScore).reversed());
    }
    public void sortByFirstName() {
        students.sort(Comparator.comparing(Students::getFirstName));
    }
}