package com.sc;

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

    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.getX() <= this.width
                && coordinate.getX() > 0
                && coordinate.getY() <= this.height
                && coordinate.getY() > 0;
    }

    public void addPixels(List<Coordinate> coordinate, String paint) {
        coordinate.stream().forEach(c -> addPixel(c, paint));
    }

    public void addPixel(Coordinate coordinate, String paint) {
        int x = coordinate.getX() - 1;
        int y = coordinate.getY() - 1;
        if (isInBounds(coordinate)) {
            this.pixels[x][y] = paint;
        }
    }
}
