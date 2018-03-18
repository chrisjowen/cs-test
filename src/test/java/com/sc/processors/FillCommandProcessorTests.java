package com.sc.processors;


import com.sc.commands.FillCommand;
import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import com.sc.model.PaintContext;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.MissingContextWhenProcessingException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


public class FillCommandProcessorTests {
    FillCommandProcessor processor = new FillCommandProcessor();
    Canvas mockedCanvas;
    PaintContext context;

    @Before
    public void before() {
        this.mockedCanvas = spy(new Canvas(3, 3));
        this.context = new PaintContext(null, this.mockedCanvas);
    }

    @Test
    public void shouldFillIfInBounds() throws CommandProcessingException {
        processor.process(new FillCommand(new Coordinate(1, 1), 'b'), context);
        List<Coordinate> expectedPixelCoords = Arrays.asList(
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(1, 2),
                new Coordinate(2, 2),
                new Coordinate(3, 1),
                new Coordinate(3, 2),
                new Coordinate(1, 3),
                new Coordinate(2, 3),
                new Coordinate(3, 3)
        );
        verify(this.mockedCanvas).addPixels(expectedPixelCoords, 'b');
    }

    @Test
    public void shouldFillOnlyInWhereAppropriate() throws CommandProcessingException {
//       Setup Canvas with line in middle
        Canvas canvas = new Canvas(3, 3);
        List<Coordinate> lineCoords = Arrays.asList(
                new Coordinate(2, 1),
                new Coordinate(2, 2),
                new Coordinate(2, 3)
        );
        canvas.addPixels(lineCoords, 'x');

//      Override spy with canvas with line in middle
        this.mockedCanvas = spy(canvas);
        this.context = new PaintContext(null, this.mockedCanvas);

//      Expect only half to be filled
        processor.process(new FillCommand(new Coordinate(1, 1), 'b'), context);
        List<Coordinate> expectedPixelCoords = Arrays.asList(
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(1, 3)
        );
        verify(this.mockedCanvas).addPixels(expectedPixelCoords, 'b');
    }

    @Test
    public void shouldAddNoPixelsWhenCommandOutOfBounds() throws CommandProcessingException {
        processor.process(new FillCommand(new Coordinate(4, 1), 'b'), context);
        verify(this.mockedCanvas).addPixels(new ArrayList<>(), 'b');
    }


    @Test(expected = MissingContextWhenProcessingException.class)
    public void shouldFailToProcessCommandWithoutContext() throws CommandProcessingException {
        processor.process(new FillCommand(new Coordinate(1, 1), 'b'), null);
    }
}
