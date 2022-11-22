package com.indignia.robotchallenge.converters;

import com.indignia.robotchallenge.dto.RobotPositionDto;
import com.indignia.robotchallenge.model.Direction;
import com.indignia.robotchallenge.model.Location;
import com.indignia.robotchallenge.model.RobotPosition;
import org.springframework.stereotype.Component;

@Component
public class RobotPositionConverter {

    public RobotPositionDto convert(RobotPosition position) {
        Location pointDto = new Location(position.location().x(), position.location().y());
        return new RobotPositionDto(pointDto, Direction.valueOf(position.facing().name()));
    }
}
