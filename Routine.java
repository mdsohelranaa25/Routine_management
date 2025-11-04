package com.university.routine.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Routine {
    private int routineId;
    private String department;
    private int semester;
    private String section;
    private Date effectiveFrom;
    private Date effectiveTo;
    private Map<String, List<CourseSchedule>> weekSchedule;
    private boolean isPublished;
    private Date publishedDate;
    private Admin createdBy;
    private Date createdAt;
    private Admin modifiedBy;
    private Date lastModified;
    private boolean isActive;
    private Admin deletedBy;
    private String academicYear;
    
    // Constructor
    public Routine() {
        this.weekSchedule = new HashMap<>();
        initializeWeekSchedule();
        this.isPublished = false;
        this.isActive = true;
        this.createdAt = new Date();
    }
    
    public Routine(String department, int semester, String section) {
        this.department = department;
        this.semester = semester;
        this.section = section;
        this.weekSchedule = new HashMap<>();
        initializeWeekSchedule();
        this.isPublished = false;
        this.isActive = true;
        this.createdAt = new Date();
    }
    
    private void initializeWeekSchedule() {
        weekSchedule.put("SUNDAY", new ArrayList<>());
        weekSchedule.put("MONDAY", new ArrayList<>());
        weekSchedule.put("TUESDAY", new ArrayList<>());
        weekSchedule.put("WEDNESDAY", new ArrayList<>());
        weekSchedule.put("THURSDAY", new ArrayList<>());
    }
    
    // Routine methods
    public void addSchedule(String day, CourseSchedule schedule) {
        if (weekSchedule.containsKey(day.toUpperCase())) {
            weekSchedule.get(day.toUpperCase()).add(schedule);
            System.out.println("Schedule added for " + day);
        } else {
            System.out.println("Invalid day: " + day);
        }
    }
    
    public void removeSchedule(String day, CourseSchedule schedule) {
        if (weekSchedule.containsKey(day.toUpperCase())) {
            weekSchedule.get(day.toUpperCase()).remove(schedule);
            System.out.println("Schedule removed for " + day);
        }
    }
    
    public List<CourseSchedule> getScheduleByDay(String day) {
        return weekSchedule.getOrDefault(day.toUpperCase(), new ArrayList<>());
    }
    
    public void generateRoutine() {
        System.out.println("=== Generating Routine ===");
        System.out.println("Department: " + department);
        System.out.println("Semester: " + semester);
        System.out.println("Section: " + section);
        // Routine generation logic
    }
    
    public boolean validateRoutine() {
        System.out.println("Validating routine...");
        
        // Check for conflicts
        for (String day : weekSchedule.keySet()) {
            List<CourseSchedule> schedules = weekSchedule.get(day);
            for (int i = 0; i < schedules.size(); i++) {
                for (int j = i + 1; j < schedules.size(); j++) {
                    if (schedules.get(i).checkConflict(schedules.get(j))) {
                        System.out.println("Conflict found on " + day);
                        return false;
                    }
                }
            }
        }
        
        System.out.println("Routine validated successfully");
        return true;
    }
    
    public void publishRoutine() {
        if (validateRoutine()) {
            this.isPublished = true;
            this.publishedDate = new Date();
            System.out.println("Routine published successfully");
        } else {
            System.out.println("Cannot publish routine with conflicts");
        }
    }
    
    public void unpublishRoutine() {
        this.isPublished = false;
        System.out.println("Routine unpublished");
    }
    
    public void exportToPDF() {
        System.out.println("