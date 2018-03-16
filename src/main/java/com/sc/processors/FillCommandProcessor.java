package com.sc.processors;


import com.sc.Canvas;
import com.sc.Coordinate;
import com.sc.commands.FillCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FillCommandProcessor extends CommandProcessor<FillCommand> {


    public FillCommandProcessor(Canvas canvas) {
        super(canvas);
    }

    @Override
    public CommandProcessor process(FillCommand command) {
        canvas.addPixels(this.generateCoordinates(command.getCoordinate()), command.getColor());
        return this;
    }

    private List<Coordinate> generateCoordinates(Coordinate coordinate) {
        List<Coordinate> toPaint = new ArrayList<>();
        List<Coordinate> toProcess = new ArrayList<>();
        String currentPixel = canvas.getPixel(coordinate);

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
