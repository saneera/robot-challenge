package com.indignia.robotchallenge.controller;

import com.indignia.robotchallenge.dto.CommandRequestDto;
import com.indignia.robotchallenge.dto.CommandResponseDto;
import com.indignia.robotchallenge.service.RobotSimulatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class RobotSimulatorController {

    private final RobotSimulatorService robertService;

    public RobotSimulatorController(RobotSimulatorService robertService) {
        this.robertService = robertService;
    }

    @PostMapping(value = "/simulate-me")
    public CommandResponseDto simulateMe(@RequestBody @NotNull CommandRequestDto request) {
        return robertService.simulateMe(request);
    }
}
