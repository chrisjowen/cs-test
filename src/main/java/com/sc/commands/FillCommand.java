package com.sc.commands;

import com.sc.model.Command;
import com.sc.model.Coordinate;
import com.sc.utils.ListUtils;
import com.sc.commands.exceptions.InvalidCommandException;

import java.util.List;

public class FillCommand implements Command {
    private Coordinate coordinate;
    private Character color;

    public FillCommand(Coordinate coordinate, Character color) {
        this.coordinate = coordinate;
        this.color = color;
    }

    public FillCommand(int x, int y, Character color) {
        this(new Coordinate(x,y), color);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Character getColor() {
        return color;
    }

    public static FillCommand fromArgs(List<String> args) throws InvalidCommandException {
        if(args.size()!=3) throw new InvalidCommandException(FillCommand.class);
        String colorString = args.remove(2);
        Character color = colorString.charAt(0);
        List<Integer> intArgs = ListUtils.stringToInt(args);
        if(intArgs.size()!=2) throw new InvalidCommandException(FillCommand.class);

        return new FillCommand(intArgs.get(0),intArgs.get(1),color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FillCommand that = (FillCommand) o;

        if (coordinate != null ? !coordinate.equals(that.coordinate) : that.coordinate != null) return false;
        return !(color != null ? !color.equals(that.color) : that.color != null);
    }

    @Override
    public int hashCode() {
        int result = coordinate != null ? coordinate.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
