package Lab13;

public class Person {
    private String fName = "*";
    private String lName = "*";
    private String mName = "*";

    public void setFName(String fname) {
        this.fName = fname;
    }

    public void setLName(String lname) {
        this.lName = lname;
    }

    public void setMname(String mname) {
        this.mName = mname;
    }

    public String getInfo(){
        return lName + " " + fName.charAt(0) + "." + mName.charAt(0);
    }
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setLName("Kokoncev");
        p1.setFName("Oleg");
        p1.setMname("Dmitrievich");
        System.out.println(p1.getInfo());
    }
}