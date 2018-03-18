package com.sc.commands.exceptions;


public class UnknownCommandException extends CommandParsingException {

    public UnknownCommandException(String command) {
        super(String.format("Unknown command: %s", command));
    }
}
