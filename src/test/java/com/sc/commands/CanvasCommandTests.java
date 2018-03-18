package com.sc.commands;
import com.sc.commands.exceptions.InvalidCommandException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class CanvasCommandTests {

    @Test
        public void shouldCreateCommandForArgs() throws InvalidCommandException {
        CanvasCommand expected = CanvasCommand.fromArgs(Arrays.asList("10", "10"));
        assertEquals(expected, new CanvasCommand(10, 10));
    }

    @Test(expected = InvalidCommandException.class)
       public void shouldFailToCreateWithTooManyArgs() throws InvalidCommandException {
        CanvasCommand.fromArgs(Arrays.asList("10", "10", "10"));
    }

    @Test(expected = InvalidCommandException.class)
        public void shouldFailToCreateWithTooFewArgs() throws InvalidCommandException {
        CanvasCommand.fromArgs(Arrays.asList("10"));
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldFailToCreateWithInvalidArgs() throws InvalidCommandException {
        CanvasCommand.fromArgs(Arrays.asList("10", "A"));
    }
}
