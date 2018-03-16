package com.sc.processors.exceptions;

public class CommandProcessingException extends Throwable {
    public CommandProcessingException() {
    }

    public CommandProcessingException(String message) {
        super(message);
    }

    public CommandProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandProcessingException(Throwable cause) {
        super(cause);
    }

    public CommandProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
