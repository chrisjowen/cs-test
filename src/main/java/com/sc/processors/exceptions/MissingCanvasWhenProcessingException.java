package com.sc.processors.exceptions;

import com.sc.model.Command;

public class MissingCanvasWhenProcessingException extends CommandProcessingException {
    public MissingCanvasWhenProcessingException(Command command) {
        super(String.format("Invalid command: `%s`. You must first init with a Canvas command", command.toString()));
    }
}


