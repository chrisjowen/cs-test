package com.sc.processors;


import com.sc.Canvas;
import com.sc.Coordinate;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.processors.exceptions.CommandProcessingException;

public class RectangleCommandProcessor extends CommandProcessor<RectangleCommand> {


    public RectangleCommandProcessor(Canvas canvas) throws CommandProcessingException {
        super(canvas);
    }

    @Override
    public CommandProcessor process(RectangleCommand command) throws CommandProcessingException {
        Coordinate a = command.getStartCoordinate();
        Coordinate c = command.getEndCoordinate();
        Coordinate b = new Coordinate(c.getX(), a.getY());
        Coordinate d = new Coordinate(a.getX(), c.getY());

        return this.then(new LineCommand(a, b))
                .then(new LineCommand(b, c))
                .then(new LineCommand(c, d))
                .then(new LineCommand(d, a));


    }


}
