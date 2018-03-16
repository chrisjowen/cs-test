package com.sc.processors;

import com.sc.Canvas;
import com.sc.TextRenderer;
import com.sc.commands.*;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.InvalidStartCommandProcessingException;

public abstract class CommandProcessor<T extends Command> {


    protected Canvas canvas;
    protected CommandProcessor() {}
    protected CommandProcessor(Canvas canvas) {
        this.canvas = canvas;
    }

    public abstract CommandProcessor process(T command) throws CommandProcessingException;

    public CommandProcessor then(Command command) throws CommandProcessingException {
        if(CanvasCommand.class.isInstance(command)){
            return new CanvasCommandProcessor().process((CanvasCommand)command);
        }
        if(RectangleCommand.class.isInstance(command)){
            return new RectangleCommandProcessor(canvas).process((RectangleCommand)command);
        }
        if(LineCommand.class.isInstance(command)){
            return new LineCommandProcessor(canvas).process((LineCommand)command);
        }
        if(FillCommand.class.isInstance(command)){
            return new FillCommandProcessor(canvas).process((FillCommand)command);
        }
        throw new InvalidStartCommandProcessingException(command);
    }

    public static CommandProcessor init(Command command) throws CommandProcessingException {
        if(CanvasCommand.class.isInstance(command)){
            return new CanvasCommandProcessor().process((CanvasCommand)command);
        }
        throw new InvalidStartCommandProcessingException(command);
    }

    public String renderWith(TextRenderer renderer) {
        return renderer.render(this.canvas);
    }
}
