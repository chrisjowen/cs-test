package com.sc.processors.exceptions;

import com.sc.model.Command;

public class MissingPainterWhenProcessingException extends CommandProcessingException {
    public MissingPainterWhenProcessingException(Command command) {
        super(String.format("Invalid command: `%s`. Missing painter in context", command.toString()));
    }
}
