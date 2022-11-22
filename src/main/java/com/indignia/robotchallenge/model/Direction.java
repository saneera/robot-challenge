package com.indignia.robotchallenge.model;

public enum Direction {
    NORTH(360, new Location(0, 1)),
    EAST(90, new Location(1, 0)),
    SOUTH(180, new Location(0, -1)),
    WEST(270, new Location(-1, 0));

    private final int degree;
    private final Location unit;

    Direction(int degree, Location unit) {
        this.degree = degree;
        this.unit = unit;
    }

    public int getDegree() {
        return degree;
    }

    public Location getUnit() {
        return unit;
    }

    public static Direction of(int degree, int angle) {
        Direction[] directions = values();
        int index = (int) Math.round((((double) degree % 360) / Math.abs(angle))) % directions.length;
        return directions[index];
    }
}
