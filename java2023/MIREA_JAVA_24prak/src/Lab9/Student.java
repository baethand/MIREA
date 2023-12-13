package Lab9;

public class Student {
    private int id;
    private String name;

    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Student otherStudent) {
        // Сравниваем объекты Student на основе iDNumber
        return Integer.compare(this.getId(), otherStudent.getId());
    }
}
