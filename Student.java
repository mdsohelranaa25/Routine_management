package models;
public class Student extends User {
    public String roll;
    public Student(String name, String email, String password, String roll) {
        super("STUDENT", name, email, password);
        this.roll = roll;
    }
}