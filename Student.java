package com.university.routine.models;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private String studentId;
    private String department;
    private int semester;
    private String section;
    private float cgpa;
    private String batch;
    private String session;
    private Date admissionDate;
    private String guardianName;
    private String guardianPhone;
    private List<Course> enrolledCourses;
    private List<Attendance> attendanceRecords;
    private String hostelName;
    private String roomNumber;
    private boolean isScholarship;
    
    // Constructor
    public Student() {
        super();
        this.role = "STUDENT";
        this.enrolledCourses = new ArrayList<>();
        this.attendanceRecords = new ArrayList<>();
    }
    
    public Student(String name, String email, String studentId, 
                   String department, int semester, String section) {
        super(name, email, studentId, "default123");
        this.studentId = studentId;
        this.department = department;
        this.semester = semester;
        this.section = section;
        this.role = "STUDENT";
        this.enrolledCourses = new ArrayList<>();
        this.attendanceRecords = new ArrayList<>();
    }
    
    @Override
    public String getDetails() {
        return String.format("Student ID: %s\nName: %s\nDepartment: %s\n" +
                           "Semester: %d\nSection: %s\nCGPA: %.2f",
                           studentId, name, department, semester, section, cgpa);
    }
    
    @Override
    public String getRole() {
        return "STUDENT";
    }
    
    // Student-specific methods
    public void viewRoutine() {
        System.out.println("=== Class Routine for " + studentId + " ===");
        System.out.println("Department: " + department);
        System.out.println("Semester: " + semester + ", Section: " + section);
    }
    
    public void viewExamSchedule() {
        System.out.println("=== Exam Schedule for " + studentId + " ===");
        for (Course course : enrolledCourses) {
            System.out.println(course.getCourseCode() + " - " + course.getCourseTitle());
        }
    }
    
    public void checkAttendance() {
        System.out.println("=== Attendance Report ===");
        for (Course course : enrolledCourses) {
            float percentage = calculateAttendancePercentage(course);
            System.out.printf("%s: %.2f%%\n", course.getCourseCode(), percentage);
            if (percentage < 75) {
                System.out.println("⚠️ WARNING: Attendance below 75%!");
            }
        }
    }
    
    private float calculateAttendancePercentage(Course course) {
        int present = 0;
        int total = 0;
        for (Attendance att : attendanceRecords) {
            if (att.getCourse().equals(course)) {
                total++;
                if (att.getStatus().equals("PRESENT")) {
                    present++;
                }
            }
        }
        return total > 0 ? (present * 100.0f / total) : 0;
    }
    
    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.addStudent(this);
            System.out.println("Enrolled in: " + course.getCourseTitle());
        }
    }
    
    public void dropCourse(Course course) {
        if (enrolledCourses.remove(course)) {
            course.removeStudent(this);
            System.out.println("Dropped: " + course.getCourseTitle());
        }
    }
    
    public void submitFeedback(Course course, int rating, String comments) {
        Feedback feedback = new Feedback(this, course, rating, comments);
        System.out.println("Feedback submitted for: " + course.getCourseCode());
    }
    
    public void downloadRoutine() {
        System.out.println("Downloading routine PDF...");
        // PDF generation logic
    }
    
    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    
    public float getCgpa() { return cgpa; }
    public void setCgpa(float cgpa) { this.cgpa = cgpa; }
    
    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }
    
    public String getSession() { return session; }
    public void setSession(String session) { this.session = session; }
    
    public Date getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(Date admissionDate) { 
        this.admissionDate = admissionDate; 
    }
    
    public String getGuardianName() { return guardianName; }
    public void setGuardianName(String guardianName) { 
        this.guardianName = guardianName; 
    }
    
    public String getGuardianPhone() { return guardianPhone; }
    public void setGuardianPhone(String guardianPhone) { 
        this.guardianPhone = guardianPhone; 
    }
    
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
    public void setEnrolledCourses(List<Course> enrolledCourses) { 
        this.enrolledCourses = enrolledCourses; 
    }
    
    public List<Attendance> getAttendanceRecords() { return attendanceRecords; }
    public void setAttendanceRecords(List<Attendance> attendanceRecords) { 
        this.attendanceRecords = attendanceRecords; 
    }
    
    public String getHostelName() { return hostelName; }
    public void setHostelName(String hostelName) { this.hostelName = hostelName; }
    
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    
    public boolean isScholarship() { return isScholarship; }
    public void setScholarship(boolean scholarship) { isScholarship = scholarship; }
    
    @Override
    public String toString() {
        return "Student{" + studentId + " - " + name + ", " + 
               department + ", Sem-" + semester + section + "}";
    }
}