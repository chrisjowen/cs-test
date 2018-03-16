package com.sc;

import com.sc.commands.*;
import com.sc.commands.exceptions.CommandException;
import com.sc.commands.exceptions.InvalidCommandException;
import com.sc.commands.exceptions.UnknownCommandException;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.junit.Assert.*;

public class CommandParserTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();
    private CommandParser commandParser = new CommandParser();


    @Test(expected = UnknownCommandException.class)
    public void shouldErrorIfCommandUnknown() throws CommandException {
        commandParser.parse("U");
    }

    @Test(expected = UnknownCommandException.class)
    public void shouldErrorIfCommandEmpty() throws CommandException {
        commandParser.parse("");
    }

    @Test
    public void shouldParseCanvasCommand() throws CommandException {
        CanvasCommand command = issueCommand("C 0 10", CanvasCommand.class);
        assertEquals(command.getWidth(), 0);
        assertEquals(command.getHeight(), 10);
    }

    @Test
    public void shouldFailParsingCanvasCommandWithTooFewArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("CanvasCommand"));

//        Attempt to parse
        commandParser.parse("C 0");
    }

    @Test
    public void shouldFailParsingCanvasCommandWithTooManyArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("CanvasCommand"));

//        Attempt to parse
        commandParser.parse("C 0 10 100");
    }

    @Test
    public void shouldFailParsingCanvasCommandWithInvalidArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("CanvasCommand"));

//        Attempt to parse
        commandParser.parse("C 0 I");
    }

    @Test
    public void shouldParseLineCommand() throws CommandException {
        LineCommand command = issueCommand("L 0 0 1 0", LineCommand.class);
        assertEquals(command.getStartCoordinate().toString(), "0,0");
        assertEquals(command.getEndCoordinate().toString(), "1,0");
    }

    @Test
    public void shouldFailParsingLineCommandWithTooFewArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Line"));

//        Attempt to parse
        commandParser.parse("L 0 0 1");
    }

    @Test
    public void shouldFailParsingLineCommandWithTooManyArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Line"));

//        Attempt to parse
        commandParser.parse("L 0 0 0 0 0");
    }

    @Test
    public void shouldFailParsingLineWithNoneIntArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Line"));

//        Attempt to parse
        commandParser.parse("L 0 0 0 A");
    }

    @Test
    public void shouldParseRectangleCommand() throws CommandException {
        RectangleCommand command = issueCommand("R 0 0 10 10", RectangleCommand.class);
        assertEquals(command.getStartCoordinate().toString(), "0,0");
        assertEquals(command.getEndCoordinate().toString(), "10,10");
    }

    @Test
    public void shouldFailParsingRectangleCommandWithTooFewArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Rectangle"));

//        Attempt to parse
        commandParser.parse("R 0 0 1");
    }


    @Test
    public void shouldFailParsingRectangleCommandWithTooManyArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Rectangle"));

//        Attempt to parse
        commandParser.parse("R 0 0 0 0 0");
    }

    @Test
    public void shouldFailParsingRectangleWithNoneIntArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Rectangle"));

//        Attempt to parse
        commandParser.parse("R 0 0 0 A");
    }

    @Test
    public void shouldParseFillCommand() throws CommandException {
        FillCommand command = issueCommand("B 0 10 Y", FillCommand.class);
        assertEquals(command.getCoordinate().toString(), "0,10");
        assertEquals(command.getColor(), "Y");
    }

    @Test
    public void shouldFailParsingFillCommandWithTooFewArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Fill"));

//        Attempt to parse
        commandParser.parse("B 0 0");
    }

    @Test
    public void shouldFailParsingFillCommandWithTooManyArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Fill"));

//        Attempt to parse
        commandParser.parse("B 0 0 A B");
    }

    @Test
    public void shouldFailParsingFillWithBadArgs() throws CommandException {
//        Setup Exception expectations
        expected.expect(InvalidCommandException.class);
        expected.expectMessage(CoreMatchers.containsString("Fill"));

//        Attempt to parse
        commandParser.parse("B 0 B A");
    }

    @Test
    public void shouldParseCommandsWithOddSpacing() throws CommandException {
        CanvasCommand command = issueCommand("C 0  10", CanvasCommand.class);
        assertEquals(command.getWidth(), 0);
        assertEquals(command.getHeight(), 10);
    }


//    NOTE: Java Type Erasure Is An Terrible Idea!!
    private <T> T issueCommand(String input, Class<T> objectClass) throws CommandException {
        Command command = commandParser.parse(input);
        assertEquals(command.getClass(), objectClass);
        return (T)command;
    }
}
