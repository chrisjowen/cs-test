package com.sc.processors;

import com.sc.CommandProcessor;
import com.sc.model.Canvas;
import com.sc.model.PaintContext;
import com.sc.commands.CanvasCommand;

public class CanvasCommandProcessor extends CommandProcessor<CanvasCommand> {
    public Canvas process(CanvasCommand command, PaintContext context) {
        return new Canvas(command.getWidth(), command.getHeight());
    }
}
