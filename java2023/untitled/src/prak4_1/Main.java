package prak4_1;

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class SchoolStudent extends Student {
    private int grade;

    public SchoolStudent(String name, int age, int grade) {
        super(name, age);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}

class CollegeStudent extends Student {
    private String university;

    public CollegeStudent(String name, int age, String university) {
        super(name, age);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }
}

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[4];
        students[0] = new SchoolStudent("John", 15, 9);
        students[1] = new SchoolStudent("Mary", 14, 8);
        students[2] = new CollegeStudent("Peter", 20, "University A");
        students[3] = new CollegeStudent("Anna", 19, "University B");

        System.out.println("List of School Students:");
        for (Student student : students) {
            if (student instanceof SchoolStudent) {
                SchoolStudent schoolStudent = (SchoolStudent) student;
                System.out.println("Name: " + schoolStudent.getName() + ", Age: " + schoolStudent.getAge() + ", Grade: " + schoolStudent.getGrade());
            }
        }

        System.out.println("\nList of College Students:");
        for (Student student : students) {
            if (student instanceof CollegeStudent) {
                CollegeStudent collegeStudent = (CollegeStudent) student;
                System.out.println("Name: " + collegeStudent.getName() + ", Age: " + collegeStudent.getAge() + ", University: " + collegeStudent.getUniversity());
            }
        }
    }
}
