package models;
public abstract class User {
    public String name, email, password, role;

    public User(String role, String name, String email, String password) {
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}