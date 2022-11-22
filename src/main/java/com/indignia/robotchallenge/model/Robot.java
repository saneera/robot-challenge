package com.indignia.robotchallenge.model;

/*
 Hold the Robot information
 */
public class Robot{

    private int id;

    private boolean isActive;

    private Location location;

    private Direction direction;

    public Robot() {
    }

    public Robot(int id, boolean isActive, Location location, Direction direction) {
        this.id = id;
        this.isActive = isActive;
        this.location = location;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Robot{" +
                ", id=" + id +
                ", isActive=" + isActive +
                ", location=" + location +
                ", direction=" + direction +
                '}';
    }
}
