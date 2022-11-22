package com.indignia.robotchallenge.model;

public record Location(int x, int y) {

    public Location add(Location point) {
        return new Location(x + point.x, y + point.y);
    }
}
