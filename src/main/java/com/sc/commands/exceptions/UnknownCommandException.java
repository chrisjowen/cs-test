package com.sc.commands.exceptions;


public class UnknownCommandException extends CommandException {

    public UnknownCommandException(String command) {
        super(String.format("Unknown command: %s", command));
    }
}
