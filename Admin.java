package models;
public class Admin extends User {
    public Admin(String name, String email, String password) {
        super("ADMIN", name, email, password);
    }
}