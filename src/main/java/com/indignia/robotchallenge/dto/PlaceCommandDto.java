package com.indignia.robotchallenge.dto;

import com.indignia.robotchallenge.model.Command;
import javax.validation.constraints.NotNull;

public class PlaceCommandDto extends CommandDto<RobotPositionDto> {

    public PlaceCommandDto(@NotNull RobotPositionDto positionDto) {
        super(Command.PLACE, positionDto);
    }
}
