package com.sc.commands;

import com.sc.commands.exceptions.InvalidCommandException;
import com.sc.model.Coordinate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FillCommandTests {

    @Test
    public void shouldCreateCommandForArgs() throws InvalidCommandException {
        List<String> args = new ArrayList<>();
        args.add("1");
        args.add("1");
        args.add("o");

        FillCommand expected = FillCommand.fromArgs(args);
        assertEquals(expected, new FillCommand(new Coordinate(1, 1), 'o'));
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldFailToCreateWithTooManyArgs() throws InvalidCommandException {
        FillCommand.fromArgs(Arrays.asList("1", "1"));
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldFailToCreateWithTooFewArgs() throws InvalidCommandException {
        FillCommand.fromArgs(Arrays.asList("1", "1", "o", "o"));
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldFailToCreateWithInvalidArgs() throws InvalidCommandException {
        FillCommand.fromArgs(Arrays.asList("1", "A"));
    }
}
