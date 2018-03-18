package com.sc;


import com.sc.model.Canvas;
import com.sc.model.Command;
import com.sc.model.PaintContext;
import com.sc.processors.exceptions.CommandProcessingException;

import java.util.Map;

public class Painter {
    protected PaintContext context = new PaintContext(this, null);
    private Map<Class, CommandProcessor> processors;

    public Painter(Map<Class, CommandProcessor> processors) {
        this.processors = processors;
    }

    public Painter paint(Command command) throws CommandProcessingException {
        this.context = new PaintContext(this, getCanvas(command));
        return this;
    }

    private Canvas getCanvas(Command command) throws CommandProcessingException {
        return this.processors.get(command.getClass()).process(command, context);
    }

    public Canvas getCanvas() {
        return this.context.getCanvas();
    }
}
