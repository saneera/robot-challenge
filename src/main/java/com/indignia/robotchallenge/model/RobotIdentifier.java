package com.indignia.robotchallenge.model;

import javax.validation.constraints.NotNull;

public record RobotIdentifier(@NotNull int id) {

    public RobotIdentifier(int id) {
        this.id = id;
    }
    @Override
    public int id() {
        return id;
    }
}
