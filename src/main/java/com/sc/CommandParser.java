package com.sc;

import com.sc.commands.*;
import com.sc.commands.exceptions.CommandException;
import com.sc.commands.exceptions.UnknownCommandException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommandParser {

    private static final String LINE_COMMAND_TYPE = "L";
    private static final String RECTANGLE_COMMAND_TYPE = "R";
    private static final String CANVAS_COMMAND_TYPE = "C";
    private static final String FILL_COMMAND_TYPE = "B";


    public Command parse(String input) throws CommandException {
        List<String> args = Arrays.asList(input.split(" "))
                .stream()
                .filter(i -> !Objects.equals(i, ""))
                .collect(Collectors.<String>toList());

        if(args.size()==0) throw new UnknownCommandException(input);
        String commandType = args.remove(0);
        return parseCommand(input, commandType, args);

    }

    private Command parseCommand(String input, String commandType, List<String> args) throws CommandException {
        switch (commandType) {
            case CANVAS_COMMAND_TYPE:
                return CanvasCommand.fromArgs(args);
            case LINE_COMMAND_TYPE:
                return LineCommand.fromArgs(args);
            case RECTANGLE_COMMAND_TYPE:
                return RectangleCommand.fromArgs(args);
            case FILL_COMMAND_TYPE:
                return FillCommand.fromArgs(args);
            default:
                throw new UnknownCommandException(input);
        }
    }
}
