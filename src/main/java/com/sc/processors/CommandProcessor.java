package com.sc.processors;

import com.sc.Painter;
import com.sc.model.Canvas;
import com.sc.model.Command;
import com.sc.model.PaintContext;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.MissingCanvasWhenProcessingException;
import com.sc.processors.exceptions.MissingContextWhenProcessingException;
import com.sc.processors.exceptions.MissingPainterWhenProcessingException;

public abstract class CommandProcessor<T extends Command> {
    public abstract Canvas process(T command, PaintContext context) throws CommandProcessingException;
    protected Canvas getCanvas(T command, PaintContext context) throws CommandProcessingException {
        if(context == null) throw new MissingContextWhenProcessingException(command);

        Canvas canvas = context.getCanvas();
        if(canvas == null) throw new MissingCanvasWhenProcessingException(command);
        return canvas;
    }

    protected Painter getPainter(T command, PaintContext context) throws CommandProcessingException {
        if(context == null) throw new MissingContextWhenProcessingException(command);

        Painter painter = context.getPainter();
        if(painter == null) throw new MissingPainterWhenProcessingException(command);
        return painter;
    }
}
