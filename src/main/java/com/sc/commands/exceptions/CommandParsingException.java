package com.sc.commands.exceptions;

public class CommandParsingException extends Throwable {
    public CommandParsingException() {
    }

    public CommandParsingException(String message) {
        super(message);
    }

    public CommandParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandParsingException(Throwable cause) {
        super(cause);
    }

    public CommandParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
