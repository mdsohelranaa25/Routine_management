package com.university.routine.models;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private String adminId;
    private String department;
    private List<String> permissions;
    private String adminLevel; // SUPER_ADMIN, DEPARTMENT_ADMIN
    private Date appointmentDate;
    private String officeLocation;
    
    // Constructor
    public Admin() {
        super();
        this.role = "ADMIN";
        this.permissions = new ArrayList<>();
        initializePermissions();
    }
    
    public Admin(String name, String email, String adminId, String department) {
        super(name, email, adminId, "admin123");
        this.adminId = adminId;
        this.department = department;
        this.role = "ADMIN";
        this.permissions = new ArrayList<>();
        initializePermissions();
    }
    
    private void initializePermissions() {
        permissions.add("CREATE_ROUTINE");
        permissions.add("UPDATE_ROUTINE");
        permissions.add("DELETE_ROUTINE");
        permissions.add("MANAGE_USERS");
        permissions.add("ASSIGN_TEACHERS");
        permissions.add("ALLOCATE_ROOMS");
        permissions.add("GENERATE_REPORTS");
        permissions.add("RESOLVE_CONFLICTS");
        permissions.add("MANAGE_EXAMS");
        permissions.add("SYSTEM_SETTINGS");
    }
    
    @Override
    public String getDetails() {
        return String.format("Admin ID: %s\nName: %s\nDepartment: %s\n" +
                           "Level: %s\nPermissions: %d",
                           adminId, name, department, adminLevel, 
                           permissions.size());
    }
    
    @Override
    public String getRole() {
        return "ADMIN";
    }
    
    // Admin-specific methods
    public Routine createRoutine(String department, int semester, String section) {
        System.out.println("=== Creating New Routine ===");
        Routine routine = new Routine(department, semester, section);
        routine.setCreatedBy(this);
        System.out.println("Routine created for: " + department + " Sem-" + 
                         semester + section);
        return routine;
    }
    
    public void updateRoutine(Routine routine) {
        System.out.println("Updating routine: " + routine.getRoutineId());
        routine.setLastModified(new Date());
        routine.setModifiedBy(this);
        System.out.println("Routine updated successfully");
    }
    
    public void deleteRoutine(Routine routine) {
        System.out.println("Deleting routine: " + routine.getRoutineId());
        routine.setActive(false);
        routine.setDeletedBy(this);
        System.out.println("Routine deleted successfully");
    }
    
    public void publishRoutine(Routine routine) {
        routine.setPublished(true);
        routine.setPublishedDate(new Date());
        System.out.println("Routine published successfully");
        
        // Send notifications to all affected users
        notifyAllUsers(routine);
    }
    
    public void assignTeacherToCourse(Teacher teacher, Course course) {
        if (checkTeacherAvailability(teacher, course)) {
            teacher.assignCourse(course);
            course.setInstructor(teacher);
            System.out.println("Teacher assigned: " + teacher.getName() + 
                             " to " + course.getCourseCode());
        } else {
            System.out.println("Teacher has conflict or reached maximum load!");
        }
    }
    
    private boolean checkTeacherAvailability(Teacher teacher, Course course) {
        // Check if teacher has time and capacity
        return teacher.getTeachingLoad() + course.getCredits() <= 12;
    }
    
    public void allocateRoom(CourseSchedule schedule, Room room) {
        if (room.isAvailable()) {
            schedule.setRoom(room);
            room.bookRoom(schedule.getDate(), schedule.getTimeSlot());
            System.out.println("Room allocated: " + room.getRoomNo());
        } else {
            System.out.println("Room not available!");
        }
    }
    
    public void addUser(User user) {
        System.out.println("Adding new user: " + user.getUsername());
        user.setCreatedAt(new Date());
        // Save to database
        System.out.println("User added successfully");
        
        // Send credentials via email
        sendCredentials(user);
    }
    
    public void updateUser(User user) {
        System.out.println("Updating user: " + user.getUsername());
        // Update in database
        System.out.println("User updated successfully");
    }
    
    public void deleteUser(User user) {
        System.out.println("Deleting user: " + user.getUsername());
        user.setActive(false);
        System.out.println("User deactivated");
    }
    
    public void resetUserPassword(User user) {
        user.generateResetToken();
        System.out.println("Password reset token sent to: " + user.getEmail());
    }
    
    public void addCourse(Course course) {
        System.out.println("Adding course: " + course.getCourseCode());
        // Save to database
        System.out.println("Course added successfully");
    }
    
    public void updateCourse(Course course) {
        System.out.println("Updating course: " + course.getCourseCode());
        // Update in database
        System.out.println("Course updated successfully");
    }
    
    public void deleteCourse(Course course) {
        System.out.println("Deleting course: " + course.getCourseCode());
        course.setActive(false);
        System.out.println("Course deactivated");
    }
    
    public void addRoom(Room room) {
        System.out.println("Adding room: " + room.getRoomNo());
        // Save to database
        System.out.println("Room added successfully");
    }
    
    public void updateRoom(Room room) {
        System.out.println("Updating room: " + room.getRoomNo());
        // Update in database
        System.out.println("Room updated successfully");
    }
    
    public void detectConflicts(Routine routine) {
        System.out.println("=== Running Conflict Detection ===");
        ConflictDetector detector = ConflictDetector.getInstance();
        List<Conflict> conflicts = detector.detectAllConflicts(routine);
        
        System.out.println("Found " + conflicts.size() + " conflicts");
        for (Conflict conflict : conflicts) {
            System.out.println("- " + conflict.getConflictType() + ": " + 
                             conflict.getDescription());
        }
    }
    
    public void resolveConflict(Conflict conflict, String resolution) {
        conflict.setResolution(resolution);
        conflict.setResolved(true);
        conflict.setResolvedDate(new Date());
        System.out.println("Conflict resolved: " + conflict.getConflictId());
    }
    
    public void generateReport(String reportType, String... parameters) {
        System.out.println("=== Generating Report: " + reportType + " ===");
        ReportGenerator generator;
        
        switch(reportType.toUpperCase()) {
            case "ROUTINE":
                generator = new RoutineReport();
                break;
            case "ATTENDANCE":
                generator = new AttendanceReportGen();
                break;
            case "CONFLICT":
                generator = new ConflictReport();
                break;
            default:
                System.out.println("Unknown report type");
                return;
        }
        generator.generateReport();
        System.out.println("Report generated successfully");
    }
    
    public void createExamSchedule(Course course, String examType, Date examDate,
                                  TimeSlot timeSlot, Room room) {
        ExamSchedule exam = new ExamSchedule(course, examType, examDate, 
                                            timeSlot, room);
        System.out.println("Exam scheduled: " + course.getCourseCode() + 
                         " - " + examType);
        
        // Notify students
        notifyStudentsAboutExam(course, exam);
    }
    
    public void assignInvigilator(ExamSchedule exam, Teacher teacher) {
        exam.addInvigilator(teacher);
        System.out.println("Invigilator assigned: " + teacher.getName());
    }
    
    public void bulkUserUpload(List<User> users) {
        System.out.println("=== Bulk User Upload ===");
        for (User user : users) {
            addUser(user);
        }
        System.out.println("Total users uploaded: " + users.size());
    }
    
    public void bulkCourseUpload(List<Course> courses) {
        System.out.println("=== Bulk Course Upload ===");
        for (Course course : courses) {
            addCourse(course);
        }
        System.out.println("Total courses uploaded: " + courses.size());
    }
    
    public void setSystemSettings(String key, String value) {
        System.out.println("Setting: " + key + " = " + value);
        // Save to configuration
    }
    
    public void backupDatabase() {
        System.out.println("Creating database backup...");
        String backupFile = "backup_" + new Date().getTime() + ".sql";
        System.out.println("Backup created: " + backupFile);
    }
    
    public void restoreDatabase(String backupFile) {
        System.out.println("Restoring database from: " + backupFile);
        // Restore logic
        System.out.println("Database restored successfully");
    }
    
    private void notifyAllUsers(Routine routine) {
        System.out.println("Sending notifications to all affected users...");
        // Notification logic
    }
    
    private void sendCredentials(User user) {
        System.out.println("Sending credentials to: " + user.getEmail());
        // Email logic
    }
    
    private void notifyStudentsAboutExam(Course course, ExamSchedule exam) {
        System.out.println("Notifying students about exam...");
        // Notification logic
    }
    
    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
    
    public void addPermission(String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }
    }
    
    public void removePermission(String permission) {
        permissions.remove(permission);
    }
    
    // Getters and Setters
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public List<String> getPermissions() { return permissions; }
    public void setPermissions(List<String> permissions) { 
        this.permissions = permissions; 
    }
    
    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }
    
    public Date getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Date appointmentDate) { 
        this.appointmentDate = appointmentDate; 
    }
    
    public String getOfficeLocation() { return officeLocation; }
    public void setOfficeLocation(String officeLocation) { 
        this.officeLocation = officeLocation; 
    }
    
    @Override
    public String toString() {
        return "Admin{" + adminId + " - " + name + ", " + 
               adminLevel + ", " + department + "}";
    }
}