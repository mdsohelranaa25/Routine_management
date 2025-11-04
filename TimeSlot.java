package com.university.routine.models;

import java.time.LocalTime;
import java.time.Duration;

public class TimeSlot {
    private int slotId;
    private String slotName;
    private LocalTime startTime;
    private LocalTime endTime;
    private int duration; // in minutes
    private String slotType; // CLASS, BREAK, LUNCH, PRAYER
    private String shift; // MORNING, DAY, EVENING
    
    // Constructor
    public TimeSlot() {}
    
    public TimeSlot(String slotName, LocalTime startTime, LocalTime endTime) {
        this.slotName = slotName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = calculateDuration();
    }
    
    public TimeSlot(String slotName, String startTime, String endTime) {
        this.slotName = slotName;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
        this.duration = calculateDuration();
    }
    
    // TimeSlot methods
    private int calculateDuration() {
        return (int) Duration.between(startTime, endTime).toMinutes();
    }
    
    public boolean isOverlapping(TimeSlot other) {
        return !(this.endTime.isBefore(other.startTime) || 
                this.endTime.equals(other.startTime) ||
                this.startTime.isAfter(other.endTime) ||
                this.startTime.equals(other.endTime));
    }
    
    public boolean contains(LocalTime time) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
    
    public String getSlotDetails() {
        return String.format("%s: %s - %s (%d mins)", 
                           slotName, startTime, endTime, duration);
    }
    
    public boolean validateTime() {
        return startTime.isBefore(endTime);
    }
    
    public String getFormattedTime() {
        return startTime.toString() + " - " + endTime.toString();
    }
    
    // Getters and Setters
    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }
    
    public String getSlotName() { return slotName; }
    public void setSlotName(String slotName) { this.slotName = slotName; }
    
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { 
        this.startTime = startTime;
        this.duration = calculateDuration();
    }
    
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { 
        this.endTime = endTime;
        this.duration = calculateDuration();
    }
    
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    public String getSlotType() { return slotType; }
    public void setSlotType(String slotType) { this.slotType = slotType; }
    
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
    
    @Override
    public String toString() {
        return slotName + " (" + startTime + " - " + endTime + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TimeSlot)) return false;
        TimeSlot other = (TimeSlot) obj;
        return this.startTime.equals(other.startTime) && 
               this.endTime.equals(other.endTime);
    }
}