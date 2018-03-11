package com.sc.commands.exceptions;


public class InvalidCommandException extends CommandException {

    public InvalidCommandException(Class type) {
        super(String.format("Invalid arguments provided for command: %s", type.getSimpleName()));
    }
}
