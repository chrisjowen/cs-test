package com.sc.processors;


import com.sc.CommandProcessor;
import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import com.sc.model.PaintContext;
import com.sc.commands.LineCommand;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.LineCommandProcessingException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LineCommandProcessor extends CommandProcessor<LineCommand> {


    @Override
    public Canvas process(LineCommand command, PaintContext context) throws CommandProcessingException {
        Canvas canvas = getCanvas(command,context);
        return canvas.addPixels(this.generateCoordinates(command), 'x');
    }

    private List<Coordinate> generateCoordinates(LineCommand command) throws CommandProcessingException {
        Coordinate startCoordinate = command.getStartCoordinate();
        Coordinate endCoordinate = command.getEndCoordinate();

        if (!startCoordinate.onSameVector(endCoordinate)) {
            throw new LineCommandProcessingException(command);
        }

        if (startCoordinate.getX() == endCoordinate.getX()) {
            return getHorizontalCoordinates(startCoordinate, endCoordinate);
        }
        return getVerticalCoordinates(startCoordinate, endCoordinate);
    }

    private List<Coordinate> getHorizontalCoordinates(Coordinate startCoordinate, Coordinate endCoordinate) {
        Coordinate a = (startCoordinate.getY() < endCoordinate.getY()) ?  startCoordinate : endCoordinate;
        Coordinate b = (a == startCoordinate) ? endCoordinate : startCoordinate;

        return IntStream.range(a.getY(), b.getY() + 1)
                .mapToObj(y -> new Coordinate(a.getX(), y))
                .collect(Collectors.<Coordinate>toList());
    }

    private List<Coordinate> getVerticalCoordinates(Coordinate startCoordinate, Coordinate endCoordinate) {
        Coordinate a = (startCoordinate.getX() < endCoordinate.getX()) ?  startCoordinate : endCoordinate;
        Coordinate b = (a == startCoordinate) ? endCoordinate : startCoordinate;

        return IntStream.range(a.getX(), b.getX() + 1)
                    .mapToObj(x -> new Coordinate(x, a.getY()))
                    .collect(Collectors.<Coordinate>toList());
    }

}
