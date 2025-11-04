package com.university.routine.models;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private String teacherId;
    private String department;
    private String designation;
    private int experience;
    private String specialization;
    private String qualification;
    private double salary;
    private Date joiningDate;
    private List<Course> assignedCourses;
    private List<String> availableDays;
    private String officeRoom;
    private String officeHours;
    private boolean isHOD;
    private int maxCoursesAllowed;
    
    // Constructor
    public Teacher() {
        super();
        this.role = "TEACHER";
        this.assignedCourses = new ArrayList<>();
        this.availableDays = new ArrayList<>();
        this.maxCoursesAllowed = 4;
    }
    
    public Teacher(String name, String email, String teacherId, 
                   String department, String designation) {
        super(name, email, teacherId, "teacher123");
        this.teacherId = teacherId;
        this.department = department;
        this.designation = designation;
        this.role = "TEACHER";
        this.assignedCourses = new ArrayList<>();
        this.availableDays = new ArrayList<>();
        this.maxCoursesAllowed = 4;
    }
    
    @Override
    public String getDetails() {
        return String.format("Teacher ID: %s\nName: %s\nDepartment: %s\n" +
                           "Designation: %s\nExperience: %d years\n" +
                           "Assigned Courses: %d",
                           teacherId, name, department, designation, 
                           experience, assignedCourses.size());
    }
    
    @Override
    public String getRole() {
        return "TEACHER";
    }
    
    // Teacher-specific methods
    public void viewTeachingSchedule() {
        System.out.println("=== Teaching Schedule for " + teacherId + " ===");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("\nAssigned Courses:");
        for (Course course : assignedCourses) {
            System.out.println("- " + course.getCourseCode() + ": " + 
                             course.getCourseTitle());
        }
    }
    
    public void markAttendance(Student student, Course course, String status) {
        Attendance attendance = new Attendance(student, course, new Date(), status);
        attendance.setMarkedBy(this);
        student.getAttendanceRecords().add(attendance);
        System.out.println("Attendance marked for: " + student.getName() + 
                         " - " + status);
    }
    
    public void markBulkAttendance(List<Student> students, Course course, 
                                  List<String> statuses) {
        for (int i = 0; i < students.size(); i++) {
            markAttendance(students.get(i), course, statuses.get(i));
        }
        System.out.println("Bulk attendance marked for " + students.size() + 
                         " students");
    }
    
    public void updateClassStatus(CourseSchedule schedule, String status, 
                                 String remarks) {
        schedule.setStatus(status);
        schedule.setRemarks(remarks);
        System.out.println("Class status updated to: " + status);
        
        // Send notifications to students
        notifyStudents(schedule.getCourse(), status, remarks);
    }
    
    public void cancelClass(CourseSchedule schedule, String reason) {
        updateClassStatus(schedule, "CANCELLED", reason);
    }
    
    public void rescheduleClass(CourseSchedule schedule, Date newDate, 
                               TimeSlot newTimeSlot) {
        schedule.setDate(newDate);
        schedule.setTimeSlot(newTimeSlot);
        schedule.setStatus("RESCHEDULED");
        System.out.println("Class rescheduled successfully");
        notifyStudents(schedule.getCourse(), "RESCHEDULED", 
                      "New date: " + newDate);
    }
    
    public void requestRoomChange(CourseSchedule schedule, Room newRoom, 
                                 String reason) {
        System.out.println("Room change request submitted");
        System.out.println("Current Room: " + schedule.getRoom().getRoomNo());
        System.out.println("Requested Room: " + newRoom.getRoomNo());
        System.out.println("Reason: " + reason);
        // Send to admin for approval
    }
    
    public void uploadMaterial(Course course, String materialType, 
                              String filePath) {
        CourseMaterial material = new CourseMaterial(course, materialType, 
                                                    filePath, this);
        System.out.println("Material uploaded: " + materialType + 
                         " for " + course.getCourseCode());
    }
    
    public void viewStudentList(Course course) {
        System.out.println("=== Student List for " + course.getCourseCode() + 
                         " ===");
        List<Student> students = course.getEnrolledStudents();
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%d. %s (%s) - %s\n", 
                            i+1, s.getName(), s.getStudentId(), s.getEmail());
        }
    }
    
    public void assignCourse(Course course) {
        if (assignedCourses.size() < maxCoursesAllowed) {
            if (!assignedCourses.contains(course)) {
                assignedCourses.add(course);
                course.setInstructor(this);
                System.out.println("Course assigned: " + course.getCourseTitle());
            }
        } else {
            System.out.println("Maximum course limit reached!");
        }
    }
    
    public void removeCourse(Course course) {
        if (assignedCourses.remove(course)) {
            course.setInstructor(null);
            System.out.println("Course removed: " + course.getCourseTitle());
        }
    }
    
    public int getTeachingLoad() {
        int totalCredits = 0;
        for (Course course : assignedCourses) {
            totalCredits += course.getCredits();
        }
        return totalCredits;
    }
    
    public boolean isAvailable(String day, TimeSlot timeSlot) {
        // Check if teacher is available on given day and time
        return availableDays.contains(day);
    }
    
    private void notifyStudents(Course course, String type, String message) {
        List<Student> students = course.getEnrolledStudents();
        for (Student student : students) {
            // Send notification
            System.out.println("Notification sent to: " + student.getName());
        }
    }
    
    // Getters and Setters
    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { 
        this.designation = designation; 
    }
    
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { 
        this.specialization = specialization; 
    }
    
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { 
        this.qualification = qualification; 
    }
    
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    
    public Date getJoiningDate() { return joiningDate; }
    public void setJoiningDate(Date joiningDate) { 
        this.joiningDate = joiningDate; 
    }
    
    public List<Course> getAssignedCourses() { return assignedCourses; }
    public void setAssignedCourses(List<Course> assignedCourses) { 
        this.assignedCourses = assignedCourses; 
    }
    
    public List<String> getAvailableDays() { return availableDays; }
    public void setAvailableDays(List<String> availableDays) { 
        this.availableDays = availableDays; 
    }
    
    public String getOfficeRoom() { return officeRoom; }
    public void setOfficeRoom(String officeRoom) { this.officeRoom = officeRoom; }
    
    public String getOfficeHours() { return officeHours; }
    public void setOfficeHours(String officeHours) { 
        this.officeHours = officeHours; 
    }
    
    public boolean isHOD() { return isHOD; }
    public void setHOD(boolean HOD) { isHOD = HOD; }
    
    public int getMaxCoursesAllowed() { return maxCoursesAllowed; }
    public void setMaxCoursesAllowed(int maxCoursesAllowed) { 
        this.maxCoursesAllowed = maxCoursesAllowed; 
    }
    
    @Override
    public String toString() {
        return "Teacher{" + teacherId + " - " + name + ", " + 
               designation + ", " + department + "}";
    }
}