package com.sc.commands;


import com.sc.Coordinate;

public class LineCommand implements Command{
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;

    public LineCommand(Coordinate startCoordinate, Coordinate endCoordinate) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
    }

    public LineCommand(int x1, int y1,int x2,int y2) {
        this(new Coordinate(x1, y1), new Coordinate(x2, y2));
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
    }

    public Coordinate getEndCoordinate() {
        return endCoordinate;
    }
}
