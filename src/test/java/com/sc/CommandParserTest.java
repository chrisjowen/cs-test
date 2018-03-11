package com.sc;

import com.sc.commands.CanvasCommand;
import com.sc.commands.Command;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.commands.exceptions.CommandParsingException;
import com.sc.commands.exceptions.InvalidCommandArgumentsException;
import com.sc.commands.exceptions.UnknownCommandParsingException;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.junit.Assert.*;

public class CommandParserTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();
    private CommandParser commandParser = new CommandParser();


    @Test(expected = UnknownCommandParsingException.class)
    public void shouldErrorIfCommandUnknown() throws CommandParsingException {
        commandParser.parse("U");
    }

    @Test(expected = UnknownCommandParsingException.class)
    public void shouldErrorIfCommandEmpty() throws CommandParsingException {
        commandParser.parse("");
    }

    @Test
    public void shouldParseCanvasCommand() throws CommandParsingException {
        CanvasCommand command = issueCommand("C 0 10", CanvasCommand.class);
        assertEquals(command.getWidth(), 0);
        assertEquals(command.getHeight(), 10);
    }

    @Test
    public void shouldFailParsingCanvasCommandWithTooFewArgs() throws CommandParsingException {
//        Setup Exception expectations
        expected.expect(InvalidCommandArgumentsException.class);
        expected.expectMessage(CoreMatchers.containsString("Canvas"));

//        Attempt to parse
        commandParser.parse("C 0");
    }

    @Test
    public void shouldFailParsingCanvasCommandWithTooManyArgs() throws CommandParsingException {
//        Setup Exception expectations
        expected.expect(InvalidCommandArgumentsException.class);
        expected.expectMessage(CoreMatchers.containsString("Canvas"));

//        Attempt to parse
        commandParser.parse("C 0 10 100");
    }

    @Test
    public void shouldParseLineCommand() throws CommandParsingException {
        LineCommand command = issueCommand("L 0 0 1 0", LineCommand.class);
        assertEquals(command.getStartCoordinate().toString(), "0,0");
        assertEquals(command.getEndCoordinate().toString(), "1,0");
    }

    @Test
    public void shouldFailParsingLineCommandWithTooFewArgs() throws CommandParsingException {
//        Setup Exception expectations
        expected.expect(InvalidCommandArgumentsException.class);
        expected.expectMessage(CoreMatchers.containsString("Line"));

//        Attempt to parse
        commandParser.parse("L 0 0 1");
    }

    @Test
    public void shouldFailParsingLineCommandWithTooManyArgs() throws CommandParsingException {
//        Setup Exception expectations
        expected.expect(InvalidCommandArgumentsException.class);
        expected.expectMessage(CoreMatchers.containsString("Line"));

//        Attempt to parse
        commandParser.parse("L 0 0 0 0 0");
    }

    @Test
    public void shouldFailParsingLineWithNoneIntArgs() throws CommandParsingException {
//        Setup Exception expectations
        expected.expect(InvalidCommandArgumentsException.class);
        expected.expectMessage(CoreMatchers.containsString("Line"));

//        Attempt to parse
        commandParser.parse("L 0 0 0 A");
    }

    @Test
    public void shouldParseRectangleCommand() throws CommandParsingException {
        RectangleCommand command = issueCommand("R 0 0 10 10", RectangleCommand.class);
        assertEquals(command.getStartCoordinate().toString(), "0,0");
        assertEquals(command.getEndCoordinate().toString(), "10,10");
    }

    @Test
    public void shouldFailParsingRectangleCommandWithTooFewArgs() throws CommandParsingException {
//        Setup Exception expectations
        expected.expect(InvalidCommandArgumentsException.class);
        expected.expectMessage(CoreMatchers.containsString("Rectangle"));

//        Attempt to parse
        commandParser.parse("R 0 0 1");
    }


    @Test
    public void shouldFailParsingRectangleCommandWithTooManyArgs() throws CommandParsingException {
//        Setup Exception expectations
        expected.expect(InvalidCommandArgumentsException.class);
        expected.expectMessage(CoreMatchers.containsString("Rectangle"));

//        Attempt to parse
        commandParser.parse("R 0 0 0 0 0");
    }

    @Test
    public void shouldFailParsingRectangleWithNoneIntArgs() throws CommandParsingException {
//        Setup Exception expectations
        expected.expect(InvalidCommandArgumentsException.class);
        expected.expectMessage(CoreMatchers.containsString("Rectangle"));

//        Attempt to parse
        commandParser.parse("R 0 0 0 A");
    }


//    NOTE: Java Type Erasure Is An Terrible Idea!!
    private <T> T issueCommand(String input, Class<T> objectClass) throws CommandParsingException {
        Command command = commandParser.parse(input);
        assertEquals(command.getClass(), objectClass);
        return (T)command;
    }
}
