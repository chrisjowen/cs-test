package com.sc.commands.exceptions;


public class UnknownCommandParsingException extends CommandParsingException {

    public UnknownCommandParsingException(String command) {
        super(String.format("Unknown command: %s", command));
    }
}
