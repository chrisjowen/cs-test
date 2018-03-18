package com.sc.commands;

import com.sc.commands.exceptions.InvalidCommandException;
import com.sc.model.Command;

import java.util.List;
import static com.sc.utils.ListUtils.stringToInt;

public class CanvasCommand implements Command {
    private int width;

    public CanvasCommand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static CanvasCommand fromArgs(List<String> args) throws InvalidCommandException {
        List<Integer> intArgs = stringToInt(args);
        if(intArgs.size()!=2) throw new InvalidCommandException(CanvasCommand.class);
        return new CanvasCommand(intArgs.get(0),intArgs.get(1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CanvasCommand that = (CanvasCommand) o;

        if (width != that.width) return false;
        return height == that.height;

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }
}
