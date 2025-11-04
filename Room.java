package com.university.routine.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private int roomId;
    private String roomNo;
    private String building;
    private int capacity;
    private String roomType; // CLASSROOM, LAB, SEMINAR_HALL, AUDITORIUM
    private List<String> facilities;
    private boolean isAvailable;
    private String floor;
    private Map<String, List<TimeSlot>> bookedSlots; // Date -> TimeSlots
    private String airConditioned;
    private boolean hasProjector;
    private boolean hasWhiteboard;
    private boolean hasSmartBoard;
    private boolean hasComputers;
    private int numberOfComputers;
    private String maintenanceStatus;
    private Date lastMaintenance;
    
    // Constructor
    public Room() {
        this.facilities = new ArrayList<>();
        this.isAvailable = true;
        this.bookedSlots = new HashMap<>();
    }
    
    public Room(String roomNo, String building, int capacity, String roomType) {
        this.roomNo = roomNo;
        this.building = building;
        this.capacity = capacity;
        this.roomType = roomType;
        this.facilities = new ArrayList<>();
        this.isAvailable = true;
        this.bookedSlots = new HashMap<>();
    }
    
    // Room methods
    public boolean checkAvailability(Date date, TimeSlot timeSlot) {
        String dateKey = date.toString();
        if (bookedSlots.containsKey(dateKey)) {
            List<TimeSlot> slots = bookedSlots.get(dateKey);
            for (TimeSlot slot : slots) {
                if (slot.isOverlapping(timeSlot)) {
                    return false;
                }
            }
        }
        return isAvailable;
    }
    
    public boolean bookRoom(Date date, TimeSlot timeSlot) {
        if (checkAvailability(date, timeSlot)) {
            String dateKey = date.toString();
            if (!bookedSlots.containsKey(dateKey)) {
                bookedSlots.put(dateKey, new ArrayList<>());
            }
            bookedSlots.get(dateKey).add(timeSlot);
            System.out.println("Room booked: " + roomNo + " on " + date);
            return true;
        }
        System.out.println("Room not available!");
        return false;
    }
    
    public void releaseRoom(Date date, TimeSlot timeSlot) {
        String dateKey = date.toString();
        if (bookedSlots.containsKey(dateKey)) {
            bookedSlots.get(dateKey).remove(timeSlot);
            System.out.println("Room released: " + roomNo);
        }
    }
    
    public void addFacility(String facility) {
        if (!facilities.contains(facility)) {
            facilities.add(facility);
        }
    }
    
    public void removeFacility(String facility) {
        facilities.remove(facility);
    }
    
    public boolean hasFacility(String facility) {
        return facilities.contains(facility);
    }
    
    public String getRoomDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Room: ").append(roomNo).append("\n");
        details.append("Building: ").append(building).append("\n");
        details.append("Floor: ").append(floor).append("\n");
        details.append("Capacity: ").append(capacity).append("\n");
        details.append("Type: ").append(roomType).append("\n");
        details.append("Facilities: ").append(String.join(", ", facilities));
        return details.toString();
    }
    
    public void setUnderMaintenance(String reason) {
        this.isAvailable = false;
        this.maintenanceStatus = reason;
        this.lastMaintenance = new Date();
        System.out.println("Room " + roomNo + " set under maintenance");
    }
    
    public void completeMaintenance() {
        this.isAvailable = true;
        this.maintenanceStatus = "COMPLETED";
        System.out.println("Room " + roomNo + " maintenance completed");
    }
    
    public List<TimeSlot> getBookedSlots(Date date) {
        String dateKey = date.toString();
        return bookedSlots.getOrDefault(dateKey, new ArrayList<>());
    }
    
    // Getters and Setters
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    
    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
    
    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }
    
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    
    public List<String> getFacilities() { return facilities; }
    public void setFacilities(List<String> facilities) { 
        this.facilities = facilities; 
    }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }
    
    public String getAirConditioned() { return airConditioned; }
    public void setAirConditioned(String airConditioned) { 
        this.airConditioned = airConditioned; 
    }
    
    public boolean isHasProjector() { return hasProjector; }
    public void setHasProjector(boolean hasProjector) { 
        this.hasProjector = hasProjector; 
    }
    
    public boolean isHasWhiteboard() { return hasWhiteboard; }
    public void setHasWhiteboard(boolean hasWhiteboard) { 
        this.hasWhiteboard = hasWhiteboard; 
    }
    
    public boolean isHasSmartBoard() { return hasSmartBoard; }
    public void setHasSmartBoard(boolean hasSmartBoard) { 
        this.hasSmartBoard = hasSmartBoard; 
    }
    
    public boolean isHasComputers() { return hasComputers; }
    public void setHasComputers(boolean hasComputers) { 
        this.hasComputers = hasComputers; 
    }
    
    public int getNumberOfComputers() { return numberOfComputers; }
    public void setNumberOfComputers(int numberOfComputers) { 
        this.numberOfComputers = numberOfComputers; 
    }
    
    public String getMaintenanceStatus() { return maintenanceStatus; }
    public void setMaintenanceStatus(String maintenanceStatus) { 
        this.maintenanceStatus = maintenanceStatus; 
    }
    
    public Date getLastMaintenance() { return lastMaintenance; }
    public void setLastMaintenance(Date lastMaintenance) { 
        this.lastMaintenance = lastMaintenance; 
    }
    
    @Override
    public String toString() {
        return roomNo + " (" + building + ") - " + roomType + 
               " [Capacity: " + capacity + "]";
    }
}