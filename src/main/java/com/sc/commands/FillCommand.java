package com.sc.commands;

import com.sc.Coordinate;
import com.sc.ListUtils;
import com.sc.commands.exceptions.InvalidCommandException;

import java.util.List;

public class FillCommand implements Command {
    private Coordinate coordinate;
    private String color;

    public FillCommand(Coordinate coordinate, String color) {
        this.coordinate = coordinate;
        this.color = color;
    }

    public FillCommand(int x, int y, String color) {
        this(new Coordinate(x,y), color);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getColor() {
        return color;
    }

    public static FillCommand fromArgs(List<String> args) throws InvalidCommandException {
        if(args.size()!=3) throw new InvalidCommandException(FillCommand.class);
        String color = args.remove(2);
        List<Integer> intArgs = ListUtils.stringToInt(args);
        if(intArgs.size()!=2) throw new InvalidCommandException(FillCommand.class);

        return new FillCommand(intArgs.get(0),intArgs.get(1),color);
    }
}
