package com.sc.processors;


import com.sc.Canvas;
import com.sc.Coordinate;
import com.sc.commands.FillCommand;
import com.sc.commands.LineCommand;
import com.sc.processors.exceptions.LineCommandProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FillCommandProcessor {

    private FillCommand command;

    public FillCommandProcessor(FillCommand command) {
        this.command = command;
    }

    public Canvas process(Canvas canvas) {
        return canvas.drawPixels(this.generateCoordinates(canvas, command.getCoordinate()), command.getColor());
    }

    private List<Coordinate> generateCoordinates(Canvas canvas, Coordinate coordinate) {
        List<Coordinate> toPaint = new ArrayList<>();
        List<Coordinate> toProcess = new ArrayList<>();
        List<Coordinate> processed = new ArrayList<>();
        String currentPixel = canvas.getPixel(coordinate);

        toProcess.add(coordinate);

        while(toProcess.size()>0) {
            Coordinate next = toProcess.remove(0);
            if(canvas.isInBounds(next) && !processed.contains(next) && Objects.equals(canvas.getPixel(next), currentPixel)) {
                toPaint.add(next);
                List<Coordinate> surroundingCoordinates = getSurroundingCoordinates(next);
                toProcess.addAll(surroundingCoordinates);
            }
            processed.add(next);
        }
        return toPaint;

//      Feels moe natural way to me, but I am scared of recursion in Java
//        return getSurroundingCoordinates(coordinate)
//                .stream()
//                .filter(canvas::isInBounds)
//                .flatMap(c -> generateCoordinates(canvas, c))
//                .collect(Collectors.<Coordinate>toList());
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
