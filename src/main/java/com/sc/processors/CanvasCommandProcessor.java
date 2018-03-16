package com.sc.processors;

import com.sc.Canvas;
import com.sc.commands.CanvasCommand;

public class CanvasCommandProcessor extends CommandProcessor<CanvasCommand> {
    public CanvasCommandProcessor() {
    }
    public CommandProcessor process(CanvasCommand command) {
        this.canvas = new Canvas(command.getWidth(), command.getHeight());
        return this;
    }
}
