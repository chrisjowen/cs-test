package com.sc.model;

import java.util.Arrays;
import java.util.List;

public class Canvas {

    private final Character[][] pixels;
    private int width, height;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new Character[width][height];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Character getPixel(Coordinate coordinate) {
        return pixels[coordinate.getX() - 1][coordinate.getY() - 1];
    }

    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.getX() <= this.width
                && coordinate.getX() > 0
                && coordinate.getY() <= this.height
                && coordinate.getY() > 0;
    }

    public Canvas addPixels(List<Coordinate> coordinate, Character paint) {
        coordinate.stream().forEach(c -> addPixel(c, paint));
        return this;
    }

    public void addPixel(Coordinate coordinate, Character paint) {
        int x = coordinate.getX() - 1;
        int y = coordinate.getY() - 1;
        if (isInBounds(coordinate)) {
            this.pixels[x][y] = paint;
        }
    }

}
