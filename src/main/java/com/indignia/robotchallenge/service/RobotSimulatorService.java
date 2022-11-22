package com.indignia.robotchallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indignia.robotchallenge.config.TableTopConfig;
import com.indignia.robotchallenge.converters.RobotPositionConverter;
import com.indignia.robotchallenge.dto.CommandRequestDto;
import com.indignia.robotchallenge.dto.CommandResponseDto;
import com.indignia.robotchallenge.exception.BadRequestException;
import com.indignia.robotchallenge.model.*;
import com.indignia.robotchallenge.processor.RobotProcessor;
import com.indignia.robotchallenge.repository.RobotSimulatorRepository;
import org.springframework.stereotype.Service;

@Service
public class RobotSimulatorService {

    private final RobotPositionConverter robotPositionConverter;

    private final RobotSimulatorRepository robotSimulatorRepository;

    private final TableTopConfig tableTopConfig;

    private final ObjectMapper objectMapper;

    public RobotSimulatorService(
            RobotPositionConverter robotPositionConverter,
            TableTopConfig tableTopConfig,
            RobotSimulatorRepository robotSimulatorRepository,
            ObjectMapper objectMapper
    ) {
        this.robotPositionConverter = robotPositionConverter;
        this.tableTopConfig = tableTopConfig;
        this.robotSimulatorRepository = robotSimulatorRepository;
        this.objectMapper = objectMapper;
    }

    public CommandResponseDto simulateMe(CommandRequestDto request) {

        Robot robot = new Robot();
        var nextId = robotSimulatorRepository.getNextSequence();
        robot.setId(nextId);
        RobotProcessor robotProcessor = new RobotProcessor(robot, tableTopConfig);

        request.getCommands().stream().forEach( commandDto -> {
            switch (commandDto.getCommand()) {
                case PLACE -> {
                    RobotPosition positionDto = objectMapper.convertValue(commandDto.getArguments(), RobotPosition.class);
                    Location location = positionDto.location();
                    robotProcessor.place(location, Direction.valueOf(positionDto.facing().toString()));
                }
                case MOVE -> {
                    robotProcessor.move();
                    ;
                }
                case LEFT -> robotProcessor.rotate(RotationDirection.LEFT);
                case RIGHT -> robotProcessor.rotate(RotationDirection.RIGHT);
                case REPORT -> robotProcessor.report();
                case ROBOT -> {
                    RobotIdentifier robotIdentifier = objectMapper.convertValue(commandDto.getArguments(), RobotIdentifier.class);
                    var existing = robotSimulatorRepository.getRobotById(robotIdentifier.id())
                            .orElseThrow(() -> new BadRequestException("No Robot found"));
                    robotProcessor.setCurrentRobot(existing);
                }
            }
        });

        var robertPosition = robotProcessor.report()
                .orElseThrow(() -> new BadRequestException("Invalid Command Supplied"));

        robotSimulatorRepository.save(robotProcessor.getCurrentRobot());
        robotSimulatorRepository.getAll().forEach(System.out::println);

        return new CommandResponseDto(robotPositionConverter.convert(robertPosition));
    }
}
