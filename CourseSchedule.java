package com.university.routine.models;

public class CourseSchedule {
    private int scheduleId;
    private Course course;
    private Teacher teacher;
    private Room room;
    private TimeSlot timeSlot;
    private String dayOfWeek; // SUNDAY, MONDAY, etc.
    private Date date;
    private String status; // SCHEDULED, COMPLETED, CANCELLED, RESCHEDULED
    private String remarks;
    private int weekNumber;
    private boolean isRecurring;
    
    // Constructor
    public CourseSchedule() {
        this.status = "SCHEDULED";
    }
    
    public CourseSchedule(Course course, Teacher teacher, Room room, 
                         TimeSlot timeSlot, String dayOfWeek) {
        this.course = course;
        this.teacher = teacher;
        this.room = room;
        this.timeSlot = timeSlot;
        this.dayOfWeek = dayOfWeek;
        this.status = "SCHEDULED";
        this.isRecurring = true;
    }
    
    // Schedule methods
    public boolean checkConflict(CourseSchedule other) {
        if (!this.dayOfWeek.equals(other.dayOfWeek)) {
            return false;
        }
        
        // Check teacher conflict
        if (this.teacher.equals(other.teacher) && 
            this.timeSlot.isOverlapping(other.timeSlot)) {
            return true;
        }
        
        // Check room conflict
        if (this.room.equals(other.room) && 
            this.timeSlot.isOverlapping(other.timeSlot)) {
            return true;
        }
        
        return false;
    }
    
    public String getScheduleDetails() {
        return String.format("""
            Course: %s
            Teacher: %s
            Room: %s
            Day: %s
            Time: %s
            Status: %s
            """,
            course.getCourseCode(),
            teacher.getName(),
            room.getRoomNo(),
            dayOfWeek,
            timeSlot.getFormattedTime(),
            status);
    }
    
    public void cancel(String reason) {
        this.status = "CANCELLED";
        this.remarks = reason;
        System.out.println("Class cancelled: " + reason);
    }
    
    public void reschedule(Date newDate, TimeSlot newTimeSlot, Room newRoom) {
        this.date = newDate;
        this.timeSlot = newTimeSlot;
        this.room = newRoom;
        this.status = "RESCHEDULED";
        System.out.println("Class rescheduled");
    }
    
    public void markCompleted() {
        this.status = "COMPLETED";
        System.out.println("Class marked as completed");
    }
    
    // Getters and Setters
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    
    public TimeSlot getTimeSlot() { return timeSlot; }
    public void setTimeSlot(TimeSlot timeSlot) { this.timeSlot = timeSlot; }
    
    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public int getWeekNumber() { return weekNumber; }
    public void setWeekNumber(int weekNumber) { this.weekNumber = weekNumber; }
    
    public boolean isRecurring() { return isRecurring; }
    public void setRecurring(boolean recurring) { isRecurring = recurring; }
    
    @Override
    public String toString() {
        return dayOfWeek + " | " + timeSlot.getSlotName() + " | " + 
               course.getCourseCode() + " | " + room.getRoomNo();
    }
}
