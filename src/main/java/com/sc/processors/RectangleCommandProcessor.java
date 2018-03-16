package com.sc.processors;


import com.sc.Canvas;
import com.sc.Coordinate;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.LineCommandProcessingException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RectangleCommandProcessor {


    private RectangleCommand command;

    public RectangleCommandProcessor(RectangleCommand command) throws CommandProcessingException {
        this.command = command;
    }

    public Canvas process(Canvas canvas) throws CommandProcessingException {
        Coordinate a = command.getStartCoordinate();
        Coordinate c = command.getEndCoordinate();
        Coordinate b = new Coordinate(c.getX(), a.getY());
        Coordinate d = new Coordinate(a.getX(), c.getY());


        return canvas.processCommand(new LineCommand(a, b))
                        .processCommand(new LineCommand(b, c))
                        .processCommand(new LineCommand(c, d))
                        .processCommand(new LineCommand(d, a));


    }



}
