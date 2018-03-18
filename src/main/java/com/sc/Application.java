package com.sc;


import com.sc.model.Command;
import com.sc.commands.exceptions.CommandParsingException;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.renderers.CanvasRenderer;

public class Application {
    private Painter painter;
    private final CommandParser parser;
    private final CanvasRenderer renderer;

    public Application(Painter painter, CommandParser parser, CanvasRenderer renderer) {
        this.painter = painter;
        this.parser = parser;
        this.renderer = renderer;
    }

    public Application processInput(String input) throws CommandProcessingException, CommandParsingException {
        Command command = parser.parse(input);
        painter = painter.paint(command);
        return this;
    }

    public Application render() {
        renderer.render(painter.getCanvas());
        return this;
    }
}
