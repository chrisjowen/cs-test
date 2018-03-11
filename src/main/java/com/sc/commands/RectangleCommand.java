package com.sc.commands;

import com.sc.Coordinate;
import com.sc.ListUtils;
import com.sc.commands.exceptions.InvalidCommandException;

import java.util.List;

public class RectangleCommand implements Command {
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;

    public RectangleCommand(Coordinate startCoordinate, Coordinate endCoordinate) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
    }

    public RectangleCommand(int x1, int y1,int x2,int y2) {
        this(new Coordinate(x1, y1), new Coordinate(x2, y2));
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
    }

    public Coordinate getEndCoordinate() {
        return endCoordinate;
    }

    public static RectangleCommand fromArgs(List<String> args) throws InvalidCommandException {
        List<Integer> intArgs = ListUtils.stringToInt(args);
        if(intArgs.size()!=4) throw new InvalidCommandException(RectangleCommand.class);
        return new RectangleCommand(intArgs.get(0),intArgs.get(1),intArgs.get(2),intArgs.get(3));
    }
}
