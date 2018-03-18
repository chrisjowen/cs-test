package com.sc.processors.exceptions;

import com.sc.model.Command;

public class MissingContextWhenProcessingException extends CommandProcessingException {
    public MissingContextWhenProcessingException(Command command) {
        super(String.format("Invalid command: `%s`. Missing painting context", command.toString()));
    }
}

