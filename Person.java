package com.university.routine.models;

import java.util.Date;

public abstract class Person {
    protected int id;
    protected String name;
    protected String email;
    protected String phone;
    protected String address;
    protected Date dateOfBirth;
    protected String gender;
    protected String bloodGroup;
    protected String nationality;
    protected String religion;
    protected String emergencyContact;
    
    // Constructor
    public Person() {}
    
    public Person(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // Abstract method - must be implemented by child classes
    public abstract String getDetails();
    public abstract String getRole();
    
    // Common methods
    public void updateProfile(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    public boolean validateEmail() {
        return email != null && email.contains("@");
    }
    
    public int getAge() {
        if (dateOfBirth == null) return 0;
        long diff = new Date().getTime() - dateOfBirth.getTime();
        return (int) (diff / (1000L * 60 * 60 * 24 * 365));
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    
    public String getReligion() { return religion; }
    public void setReligion(String religion) { this.religion = religion; }
    
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { 
        this.emergencyContact = emergencyContact; 
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', email='" + email + "'}";
    }
}