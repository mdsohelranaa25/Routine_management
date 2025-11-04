package com.university.routine.models;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private String courseCode;
    private String courseTitle;
    private int credits;
    private String department;
    private int semester;
    private String courseType; // THEORY, LAB, PROJECT
    private int maxStudents;
    private List<Student> enrolledStudents;
    private Teacher instructor;
    private Teacher labInstructor;
    private List<String> prerequisites;
    private String syllabus;
    private int totalClasses;
    private boolean isActive;
    private String description;
    
    // Constructor
    public Course() {
        this.enrolledStudents = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
        this.isActive = true;
    }
    
    public Course(String courseCode, String courseTitle, int credits, 
                 String department) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.credits = credits;
        this.department = department;
        this.enrolledStudents = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
        this.isActive = true;
        this.maxStudents = 60;
    }
    
    // Course methods
    public boolean addStudent(Student student) {
        if (enrolledStudents.size() < maxStudents) {
            if (!enrolledStudents.contains(student)) {
                enrolledStudents.add(student);
                System.out.println("Student enrolled: " + student.getName());
                return true;
            }
        } else {
            System.out.println("Course is full! Maximum capacity reached.");
        }
        return false;
    }
    
    public boolean removeStudent(Student student) {
        if (enrolledStudents.remove(student)) {
            System.out.println("Student removed: " + student.getName());
            return true;
        }
        return false;
    }
    
    public void assignTeacher(Teacher teacher) {
        this.instructor = teacher;
        System.out.println("Teacher assigned: " + teacher.getName());
    }
    
    public void assignLabInstructor(Teacher teacher) {
        this.labInstructor = teacher;
        System.out.println("Lab instructor assigned: " + teacher.getName());
    }
    
    public void addPrerequisite(String courseCode) {
        if (!prerequisites.contains(courseCode)) {
            prerequisites.add(courseCode);
        }
    }
    
    public boolean checkPrerequisites(Student student) {
        // Check if student has completed prerequisites
        return true; // Simplified
    }
    
    public int getAvailableSeats() {
        return maxStudents - enrolledStudents.size();
    }
    
    public boolean isFull() {
        return enrolledStudents.size() >= maxStudents;
    }
    
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    public String getCourseDetails() {
        return String.format("""
            Course Code: %s
            Course Title: %s
            Credits: %d
            Department: %s
            Semester: %d
            Type: %s
            Instructor: %s
            Enrolled: %d/%d
            """, 
            courseCode, courseTitle, credits, department, semester, 
            courseType, instructor != null ? instructor.getName() : "N/A",
            enrolledStudents.size(), maxStudents);
    }
    
    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { 
        this.courseTitle = courseTitle; 
    }
    
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    
    public String getCourseType() { return courseType; }
    public void setCourseType(String courseType) { this.courseType = courseType; }
    
    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    
    public Teacher getInstructor() { return instructor; }
    public void setInstructor(Teacher instructor) { this.instructor = instructor; }
    
    public Teacher getLabInstructor() { return labInstructor; }
    public void setLabInstructor(Teacher labInstructor) { 
        this.labInstructor = labInstructor; 
    }
    
    public List<String> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<String> prerequisites) { 
        this.prerequisites = prerequisites; 
    }
    
    public String getSyllabus() { return syllabus; }
    public void setSyllabus(String syllabus) { this.syllabus = syllabus; }
    
    public int getTotalClasses() { return totalClasses; }
    public void setTotalClasses(int totalClasses) { 
        this.totalClasses = totalClasses; 
    }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
    }
    
    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
    
    @Override
    public String toString() {
        return courseCode + " - " + courseTitle + " (" + credits + " credits)";
    }
}