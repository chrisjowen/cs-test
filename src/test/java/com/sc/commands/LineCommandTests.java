package com.sc.commands;
import com.sc.commands.exceptions.InvalidCommandException;
import com.sc.model.Coordinate;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class LineCommandTests {

    @Test
        public void shouldCreateCommandForArgs() throws InvalidCommandException {
        LineCommand expected = LineCommand.fromArgs(Arrays.asList("1", "1", "1", "10"));
        assertEquals(expected, new LineCommand(new Coordinate(1, 1), new Coordinate(1, 10)));
    }

    @Test(expected = InvalidCommandException.class)
       public void shouldFailToCreateWithTooManyArgs() throws InvalidCommandException {
        LineCommand.fromArgs(Arrays.asList("1", "1", "1", "10", "11"));
    }

    @Test(expected = InvalidCommandException.class)
        public void shouldFailToCreateWithTooFewArgs() throws InvalidCommandException {
        LineCommand.fromArgs(Arrays.asList("10"));
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldFailToCreateWithInvalidArgs() throws InvalidCommandException {
        LineCommand.fromArgs(Arrays.asList("10", "A"));
    }
}
