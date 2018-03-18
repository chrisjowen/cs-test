package com.sc.commands;
import com.sc.commands.exceptions.InvalidCommandException;
import com.sc.model.Coordinate;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class RectangleCommandTests {

    @Test
        public void shouldCreateCommandForArgs() throws InvalidCommandException {
        RectangleCommand expected = RectangleCommand.fromArgs(Arrays.asList("1", "1", "1", "10"));
        assertEquals(expected, new RectangleCommand(new Coordinate(1, 1), new Coordinate(1, 10)));
    }

    @Test(expected = InvalidCommandException.class)
       public void shouldFailToCreateWithTooManyArgs() throws InvalidCommandException {
        RectangleCommand.fromArgs(Arrays.asList("1", "1", "1", "10", "11"));
    }

    @Test(expected = InvalidCommandException.class)
        public void shouldFailToCreateWithTooFewArgs() throws InvalidCommandException {
        RectangleCommand.fromArgs(Arrays.asList("1", "1"));
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldFailToCreateWithInvalidArgs() throws InvalidCommandException {
        RectangleCommand.fromArgs(Arrays.asList("1", "A"));
    }
}
