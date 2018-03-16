package com.sc;

import com.sc.commands.FillCommand;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.processors.FillCommandProcessor;
import com.sc.processors.LineCommandProcessor;
import com.sc.processors.RectangleCommandProcessor;
import com.sc.processors.exceptions.CommandProcessingException;

import java.util.List;

public class Canvas {
    private final String[][] pixels;
    private int width, height;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new String[width][height];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getPixel(Coordinate coordinate) {
        return pixels[coordinate.getX() - 1][coordinate.getY() - 1];
    }

    public String getPixel(int x, int y) {
        return getPixel(new Coordinate(x, y));
    }

    public Canvas processCommand(FillCommand command) throws CommandProcessingException {
        return new FillCommandProcessor(command).process(this);
    }

    public Canvas processCommand(LineCommand command) throws CommandProcessingException {
        return new LineCommandProcessor(command).process(this);
    }

    public Canvas processCommand(RectangleCommand command) throws CommandProcessingException {
        return new RectangleCommandProcessor(command).process(this);
    }

    public Canvas drawPixels(List<Coordinate> coordinates, String paint) {
        for (Coordinate coordinate : coordinates) {
            drawPixel(coordinate, paint);
        }
        return this;
    }

    private Canvas drawPixel(Coordinate coordinate, String paint) {
        int x = coordinate.getX() - 1;
        int y = coordinate.getY() - 1;

        if (isInBounds(coordinate)) {
            this.pixels[x][y] = paint;
        }
        return this;
    }

    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.getX() <= this.width
                && coordinate.getX() > 0
                && coordinate.getY() <= this.height
                && coordinate.getY() > 0;
    }


}
