package com.indignia.robotchallenge.dto;

import com.indignia.robotchallenge.model.Command;

import javax.validation.constraints.NotNull;

public class CommandDto<T> {
    @NotNull
    private Command command;

    private T arguments;

    public CommandDto(Command command) {
        this.command = command;
        this.arguments = null;
    }

    public CommandDto(Command command, T arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public T getArguments() {
        return arguments;
    }

    public void setArguments(T arguments) {
        this.arguments = arguments;
    }
}
