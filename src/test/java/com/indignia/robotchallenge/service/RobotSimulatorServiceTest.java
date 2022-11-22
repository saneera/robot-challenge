package com.indignia.robotchallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indignia.robotchallenge.config.TableTopConfig;
import com.indignia.robotchallenge.converters.RobotPositionConverter;
import com.indignia.robotchallenge.dto.*;
import com.indignia.robotchallenge.model.Command;
import com.indignia.robotchallenge.model.Direction;
import com.indignia.robotchallenge.model.Location;
import com.indignia.robotchallenge.repository.RobotSimulatorRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RobotSimulatorServiceTest {

    @InjectMocks
    private RobotSimulatorService  robotSimulatorService;

    @Mock
    private RobotPositionConverter robotPositionConverter;

    @Mock
    private TableTopConfig tableTopConfig;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private RobotSimulatorRepository robotSimulatorRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        tableTopConfig = new TableTopConfig();
        tableTopConfig.setColumns(5);
        tableTopConfig.setRows(5);
        robotPositionConverter = new RobotPositionConverter();
        robotSimulatorService=  new RobotSimulatorService(
                robotPositionConverter,
                tableTopConfig,
                robotSimulatorRepository,
                objectMapper
        );
    }


    @Test
    public void testPlaceAndLeftAndReport() {
        given(robotSimulatorRepository.getNextSequence())
                .willReturn(1);
        RobotPositionDto position = new RobotPositionDto(new Location(0, 0), Direction.NORTH);
        List<CommandDto> commands = new ArrayList<CommandDto>() {{
            add(new PlaceCommandDto(position));
            add(new CommandDto(Command.LEFT));
            add(new CommandDto(Command.REPORT));
        }};
        CommandRequestDto request = new CommandRequestDto(commands);

        CommandResponseDto actual = robotSimulatorService.simulateMe(request);
        CommandResponseDto expected = new CommandResponseDto(new RobotPositionDto(new Location(0, 0), Direction.WEST));
        assertThat(actual.getPosition().facing()).isEqualTo(expected.getPosition().facing());
        assertThat(actual.getPosition().location().x()).isEqualTo(expected.getPosition().location().x());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
        assertThat(actual.getPosition().location().y()).isEqualTo(expected.getPosition().location().y());
    }

}
