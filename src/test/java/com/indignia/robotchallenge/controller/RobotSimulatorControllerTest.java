package com.indignia.robotchallenge.controller;

import com.indignia.robotchallenge.RobotChallengeApplication;
import com.indignia.robotchallenge.dto.*;
import com.indignia.robotchallenge.model.Command;
import com.indignia.robotchallenge.model.Direction;
import com.indignia.robotchallenge.model.Location;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RobotChallengeApplication.class)
public class RobotSimulatorControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testPlaceAndMoveOne() {
        RobotPositionDto position = new RobotPositionDto(new Location(0, 0), Direction.NORTH);
        List<CommandDto> commands = new ArrayList<CommandDto>() {{
            add(new PlaceCommandDto(position));
            add(new CommandDto(Command.MOVE));
            add(new CommandDto(Command.REPORT));
        }};
        CommandRequestDto request = new CommandRequestDto(commands);

        CommandResponseDto actual = this.restTemplate.postForObject("/api/simulate-me", request, CommandResponseDto.class);

        CommandResponseDto expected = new CommandResponseDto(new RobotPositionDto(new Location(0, 1), Direction.NORTH));
        assertThat(actual.getPosition().facing()).isEqualTo(expected.getPosition().facing());
        assertThat(actual.getPosition().location().x()).isEqualTo(expected.getPosition().location().x());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
    }

    @Test
    public void testPlaceAndLeftAndReport() {
        RobotPositionDto position = new RobotPositionDto(new Location(0, 0), Direction.NORTH);
        List<CommandDto> commands = new ArrayList<CommandDto>() {{
            add(new PlaceCommandDto(position));
            add(new CommandDto(Command.LEFT));
            add(new CommandDto(Command.REPORT));
        }};
        CommandRequestDto request = new CommandRequestDto(commands);

        CommandResponseDto actual = this.restTemplate.postForObject("/api/simulate-me", request, CommandResponseDto.class);

        CommandResponseDto expected = new CommandResponseDto(new RobotPositionDto(new Location(0, 0), Direction.WEST));
        assertThat(actual.getPosition().facing()).isEqualTo(expected.getPosition().facing());
        assertThat(actual.getPosition().location().x()).isEqualTo(expected.getPosition().location().x());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
    }

    @Test
    public void testPlaceAndMoveAndMoveAndLeftAndMoveAndAndReport() {
        RobotPositionDto position = new RobotPositionDto(new Location(1, 2), Direction.EAST);
        List<CommandDto> commands = new ArrayList<CommandDto>() {{
            add(new PlaceCommandDto(position));
            add(new CommandDto(Command.MOVE));
            add(new CommandDto(Command.MOVE));
            add(new CommandDto(Command.LEFT));
            add(new CommandDto(Command.MOVE));
            add(new CommandDto(Command.REPORT));
        }};
        CommandRequestDto request = new CommandRequestDto(commands);

        CommandResponseDto actual = this.restTemplate.postForObject("/api/simulate-me", request, CommandResponseDto.class);

        CommandResponseDto expected = new CommandResponseDto(new RobotPositionDto(new Location(3, 3), Direction.NORTH));
        assertThat(actual.getPosition().facing()).isEqualTo(expected.getPosition().facing());
        assertThat(actual.getPosition().location().x()).isEqualTo(expected.getPosition().location().x());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
    }


    @Test
    public void testPlaceIgnoreCommandsWhenRobotCannotMoveForward() {
        RobotPositionDto position = new RobotPositionDto(new Location(0, 0), Direction.SOUTH);
        List<CommandDto> commands = new ArrayList<CommandDto>() {{
            add(new PlaceCommandDto(position));
            add(new CommandDto(Command.MOVE));
            add(new CommandDto(Command.REPORT));
        }};
        CommandRequestDto request = new CommandRequestDto(commands);

        CommandResponseDto actual = this.restTemplate.postForObject("/api/simulate-me", request, CommandResponseDto.class);

        CommandResponseDto expected = new CommandResponseDto(new RobotPositionDto(new Location(0, 0), Direction.SOUTH));
        assertThat(actual.getPosition().facing()).isEqualTo(expected.getPosition().facing());
        assertThat(actual.getPosition().location().x()).isEqualTo(expected.getPosition().location().x());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
    }

}
