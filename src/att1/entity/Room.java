package att1.entity;

import att1.enums.RoomType;

public class Room {
    private int id;
    private int roomNumber;
    private String description;
    private double rating;
    private RoomType roomType;
    private double dailyRate;
    private int capacity;
    private int numBeds;
    private boolean isReserved;

    public Room() {
    }

    public Room(int id, int roomNumber, String description, double rating, RoomType roomType,
                double dailyRate, int capacity, int numBeds, boolean isReserved) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.description = description;
        this.rating = rating;
        this.roomType = roomType;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        this.numBeds = numBeds;
        this.isReserved = isReserved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return getRoomNumber() + " - " + getDescription() + " - " + getDailyRate() + " - " + getRating() + " - " + isReserved();
    }
}