package com.sc.processors;


import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import com.sc.model.PaintContext;
import com.sc.Painter;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.processors.exceptions.CommandProcessingException;

public class RectangleCommandProcessor extends CommandProcessor<RectangleCommand> {


    @Override
    public Canvas process(RectangleCommand command, PaintContext context) throws CommandProcessingException {
        Painter painter = getPainter(command, context);
        Coordinate a = command.getStartCoordinate();
        Coordinate c = command.getEndCoordinate();
        Coordinate b = new Coordinate(c.getX(), a.getY());
        Coordinate d = new Coordinate(a.getX(), c.getY());

        return painter.paint(new LineCommand(a, b))
                .paint(new LineCommand(b, c))
                .paint(new LineCommand(c, d))
                .paint(new LineCommand(d, a))
                .getCanvas();

    }


}
