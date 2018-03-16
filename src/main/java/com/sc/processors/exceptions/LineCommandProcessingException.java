package com.sc.processors.exceptions;


import com.sc.commands.CanvasCommand;
import com.sc.commands.LineCommand;

public class LineCommandProcessingException extends CommandProcessingException {
    public LineCommandProcessingException(LineCommand command) {
        super(String.format("Invalid command: `%s` Lines can only be vertical or horizontal", command.toString()));
    }
}

