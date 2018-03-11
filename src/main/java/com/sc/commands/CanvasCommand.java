package com.sc.commands;

import com.sc.commands.exceptions.InvalidCommandException;
import java.util.List;
import static com.sc.ListUtils.stringToInt;

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


}
