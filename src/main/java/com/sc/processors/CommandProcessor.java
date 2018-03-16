package com.sc.processors;

import com.sc.Canvas;
import com.sc.TextRenderer;
import com.sc.commands.*;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.InvalidStartCommandProcessingException;


public abstract class CommandProcessor<T extends Command> {


    protected Canvas canvas;

    protected CommandProcessor() {
    }

    protected CommandProcessor(Canvas canvas) {
        this.canvas = canvas;
    }

    public abstract CommandProcessor process(T command) throws CommandProcessingException;

    public CommandProcessor then(FillCommand command) throws CommandProcessingException {
        return new FillCommandProcessor(canvas).process(command);
    }

    public CommandProcessor then(LineCommand command) throws CommandProcessingException {
        return new LineCommandProcessor(canvas).process(command);
    }

    public CommandProcessor then(RectangleCommand command) throws CommandProcessingException {
        return new RectangleCommandProcessor(canvas).process(command);
    }

    public static CommandProcessor init(CanvasCommand command) {
        return new CanvasCommandProcessor().process(command);
    }

    public static CommandProcessor init(Command command) throws CommandProcessingException {
        throw new InvalidStartCommandProcessingException(command);
    }

    public String renderWith(TextRenderer renderer) {
        return renderer.render(this.canvas);
    }
}
