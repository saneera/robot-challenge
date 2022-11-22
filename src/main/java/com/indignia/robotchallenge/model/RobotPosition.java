package com.indignia.robotchallenge.model;

import javax.validation.constraints.NotNull;

public record RobotPosition(@NotNull Location location, @NotNull Direction facing) {
    public RobotPosition(Location location, Direction facing) {
        this.location = location;
        this.facing = facing;
    }

    @Override
    public Location location() {
        return location;
    }

    @Override
    public Direction facing() {
        return facing;
    }
}
