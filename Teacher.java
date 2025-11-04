package models;
public class Teacher extends User {
    public String department;
    public Teacher(String name, String email, String password, String dept) {
        super("TEACHER", name, email, password);
        this.department = dept;
    }
}