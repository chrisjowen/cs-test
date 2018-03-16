package com.sc.processors.exceptions;

import com.sc.commands.CanvasCommand;
import com.sc.commands.Command;

public class InvalidStartCommandProcessingException extends CommandProcessingException {
    public InvalidStartCommandProcessingException(Command command) {
        super(String.format("Invalid command: `%s`. You must first init with a Canvas command", command.toString()));
    }
}
