package Lab10;

public class Student {
    private String firstName;
    private String secondName;
    private String spec;
    private int course;
    private String group;
    private double score;

    public Student(String fN, String sN, String spec, int course, String gr, double score){
        this.course = course;
        this.firstName = fN;
        this.spec = spec;
        this.secondName = sN;
        this.group = gr;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getCourse() {
        return course;
    }

    public String getGroup() {
        return group;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSpec() {
        return spec;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
