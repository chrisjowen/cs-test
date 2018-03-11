package com.sc;


import com.sc.commands.CanvasCommand;
import com.sc.commands.Command;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.commands.exceptions.CommandParsingException;
import com.sc.commands.exceptions.InvalidCommandArgumentsException;
import com.sc.commands.exceptions.UnknownCommandParsingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandParser {

    private static final String LINE_COMMAND_TYPE = "L";
    private static final int LINE_COMMAND_ARGS = 4;

    private static final String RECTANGLE_COMMAND_TYPE = "R";
    private static final int RECTANGLE_COMMAND_ARGS = 4;

    private static final String CANVAS_COMMAND_TYPE = "C";
    private static final int CANVAS_COMMAND_ARGS = 2;



    public Command parse(String input) throws CommandParsingException {
        ArrayList<String> args = new ArrayList<>(Arrays.asList(input.split(" ")));
        String commandType = args.remove(0);

        switch (commandType) {
            case CANVAS_COMMAND_TYPE:
                return canvasCommand(args);
            case LINE_COMMAND_TYPE:
                return lineCommand(args);
            case RECTANGLE_COMMAND_TYPE:
                return rectangleCommand(args);
            default:
                throw new UnknownCommandParsingException(input);
        }
    }

    private Command canvasCommand(ArrayList<String> args) throws InvalidCommandArgumentsException {
        List<Integer> intArgs = convertArgs(args);
        if(intArgs.size()!=CANVAS_COMMAND_ARGS) throw new InvalidCommandArgumentsException(CanvasCommand.class);
        return new CanvasCommand(intArgs.get(0),intArgs.get(1));
    }

    private LineCommand lineCommand(List<String> args) throws CommandParsingException {
        List<Integer> intArgs = convertArgs(args);
        if(intArgs.size()!=LINE_COMMAND_ARGS) throw new InvalidCommandArgumentsException(LineCommand.class);
        return new LineCommand(intArgs.get(0),intArgs.get(1),intArgs.get(2),intArgs.get(3));
    }

    private RectangleCommand rectangleCommand(List<String> args) throws InvalidCommandArgumentsException {
        List<Integer> intArgs = convertArgs(args);
        if(intArgs.size()!=RECTANGLE_COMMAND_ARGS) throw new InvalidCommandArgumentsException(RectangleCommand.class);

        return new RectangleCommand(intArgs.get(0),intArgs.get(1),intArgs.get(2),intArgs.get(3));
    }

    private List<Integer> convertArgs(List<String> input) {
        try {
            return input.stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.<Integer>toList());

        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
    }

}
