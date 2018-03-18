package com.sc.processors;


import com.sc.CommandProcessor;
import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import com.sc.model.PaintContext;
import com.sc.commands.FillCommand;
import com.sc.processors.exceptions.CommandProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FillCommandProcessor extends CommandProcessor<FillCommand> {


    @Override
    public Canvas process(FillCommand command, PaintContext context) throws CommandProcessingException {
        Canvas canvas = getCanvas(command,context);
        return canvas.addPixels(this.generateCoordinates(command.getCoordinate(), canvas), command.getColor());
    }

    private List<Coordinate> generateCoordinates(Coordinate coordinate, Canvas canvas) {
        List<Coordinate> toPaint = new ArrayList<>();
        List<Coordinate> toProcess = new ArrayList<>();
        Character currentPixel = canvas.getPixel(coordinate);

        toProcess.add(coordinate);

        while(toProcess.size()>0) {
            Coordinate next = toProcess.remove(0);
            if(canvas.isInBounds(next) && !toPaint.contains(next) && Objects.equals(canvas.getPixel(next), currentPixel)) {
                toPaint.add(next);
                List<Coordinate> surroundingCoordinates = getSurroundingCoordinates(next);
                toProcess.addAll(surroundingCoordinates);
            }
        }
        return toPaint;
    }

//  Get all coordinates surrounding the given coordinate
    private List<Coordinate> getSurroundingCoordinates(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        return new ArrayList<Coordinate>() {
            {
                add(new Coordinate(x - 1, y)); // left
                add(new Coordinate(x + 1, y)); // right
                add(new Coordinate(x, y - 1)); // down
                add(new Coordinate(x, y + 1)); // up
                add(new Coordinate(x + 1, y + 1)); // up-right
                add(new Coordinate(x - 1, y + 1)); // up-left
                add(new Coordinate(x + 1, y - 1)); // down-right
                add(new Coordinate(x - 1, y - 1)); // down-left
            }
        };
    }

}
