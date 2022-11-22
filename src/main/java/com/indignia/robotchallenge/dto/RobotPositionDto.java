package com.indignia.robotchallenge.dto;

import com.indignia.robotchallenge.model.Direction;
import com.indignia.robotchallenge.model.Location;

import javax.validation.constraints.NotNull;

public record RobotPositionDto(@NotNull Location location, @NotNull Direction facing) {
    public RobotPositionDto(Location location, Direction facing) {
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
