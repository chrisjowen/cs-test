package com.sc.commands.exceptions;


public class InvalidCommandArgumentsException extends CommandParsingException {

    public InvalidCommandArgumentsException(Class type) {
        super(String.format("Invalid arguments provided for command: %s", type));
    }
}
