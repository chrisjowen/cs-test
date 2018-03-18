package com.sc.processors.exceptions;

import com.sc.model.Command;

public class MissingCommandProcessorException extends CommandProcessingException {
    public MissingCommandProcessorException(Command command) {
        super(String.format("No processor registered for command: `%s`", command.getClass().getSimpleName()));
    }
}
