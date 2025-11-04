package com.university.routine.models;

import java.util.Date;

public abstract class User extends Person {
    protected String username;
    protected String password;
    protected String role;
    protected Date lastLogin;
    protected boolean isActive;
    protected Date createdAt;
    protected String profilePicture;
    protected String resetToken;
    protected Date tokenExpiry;
    
    // Constructor
    public User() {
        this.isActive = true;
        this.createdAt = new Date();
    }
    
    public User(String name, String email, String username, String password) {
        super(name, email, null);
        this.username = username;
        this.password = password;
        this.isActive = true;
        this.createdAt = new Date();
    }
    
    // Authentication methods
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    
    public void login() {
        this.lastLogin = new Date();
        System.out.println(username + " logged in successfully!");
    }
    
    public void logout() {
        System.out.println(username + " logged out!");
    }
    
    public boolean changePassword(String oldPassword, String newPassword) {
        if (authenticate(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }
    
    public void generateResetToken() {
        this.resetToken = generateRandomToken();
        this.tokenExpiry = new Date(System.currentTimeMillis() + 3600000); // 1 hour
    }
    
    private String generateRandomToken() {
        return java.util.UUID.randomUUID().toString();
    }
    
    public boolean resetPassword(String token, String newPassword) {
        if (this.resetToken.equals(token) && 
            new Date().before(tokenExpiry)) {
            this.password = newPassword;
            this.resetToken = null;
            this.tokenExpiry = null;
            return true;
        }
        return false;
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public Date getLastLogin() { return lastLogin; }
    public void setLastLogin(Date lastLogin) { this.lastLogin = lastLogin; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { 
        this.profilePicture = profilePicture; 
    }
}