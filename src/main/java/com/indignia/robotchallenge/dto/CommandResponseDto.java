package com.indignia.robotchallenge.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class CommandResponseDto {
    private RobotPositionDto position;
    @JsonCreator
    public CommandResponseDto(RobotPositionDto position) {
        this.position = position;
    }

    public RobotPositionDto getPosition() {
        return position;
    }

    public void setPosition(RobotPositionDto position) {
        this.position = position;
    }
}
