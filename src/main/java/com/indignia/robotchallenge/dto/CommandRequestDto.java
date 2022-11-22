package com.indignia.robotchallenge.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.List;

public class CommandRequestDto {
    private List<CommandDto> commands;

    @JsonCreator
    public CommandRequestDto(List<CommandDto> commands) {
        this.commands = commands;
    }
    public List<CommandDto> getCommands() {
        return commands;
    }

    public void setCommands(List<CommandDto> commands) {
        this.commands = commands;
    }
}
