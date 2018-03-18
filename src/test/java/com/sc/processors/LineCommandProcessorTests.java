package com.sc.processors;


import com.sc.Painter;
import com.sc.commands.LineCommand;
import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import com.sc.model.PaintContext;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.LineCommandProcessingException;
import com.sc.processors.exceptions.MissingContextWhenProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;


public class LineCommandProcessorTests {
    LineCommandProcessor processor = new LineCommandProcessor();
    Canvas mockedCanvas;
    PaintContext context;

    @Before
    public void before() {
        this.mockedCanvas = mock(Canvas.class);
        this.context = new PaintContext(null, this.mockedCanvas);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(this.mockedCanvas);
    }

    @Test
    public void shouldAddHorizontalLinePixelsToCanvas() throws CommandProcessingException {
        processor.process(new LineCommand(new Coordinate(1, 1), new Coordinate(1, 3)), context);
        List<Coordinate> expectedPixelCoords = Arrays.asList(
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(1, 3)
        );
        verify(this.mockedCanvas).addPixels(expectedPixelCoords, 'x');
    }

    @Test
    public void shouldAddHorizontalLinePixelsToCanvasForReversedArgs() throws CommandProcessingException {
        processor.process(new LineCommand(new Coordinate(1, 3), new Coordinate(1, 1)), context);
        List<Coordinate> expectedPixelCoords = Arrays.asList(
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(1, 3)
        );
        verify(this.mockedCanvas).addPixels(expectedPixelCoords, 'x');
    }

    @Test
    public void shouldAddVerticalLinePixelsToCanvas() throws CommandProcessingException {
        processor.process(new LineCommand(new Coordinate(1, 1), new Coordinate(3, 1)), context);
        List<Coordinate> expectedPixelCoords = Arrays.asList(
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(3, 1)
        );
        verify(this.mockedCanvas).addPixels(expectedPixelCoords, 'x');
    }

    @Test
    public void shouldAddVerticalLinePixelsToCanvasForReversedArguments() throws CommandProcessingException {
        processor.process(new LineCommand(new Coordinate(3, 1), new Coordinate(1, 1)), context);
        List<Coordinate> expectedPixelCoords = Arrays.asList(
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(3, 1)
        );
        verify(this.mockedCanvas).addPixels(expectedPixelCoords, 'x');
    }


    @Test(expected = LineCommandProcessingException.class)
    public void shouldErrorIfNorHorizontalOrVerticalLines() throws CommandProcessingException {
        processor.process(new LineCommand(new Coordinate(1, 1), new Coordinate(2, 2)), context);
    }

    @Test(expected = MissingContextWhenProcessingException.class)
    public void shouldFailToProcessCommandWithoutContext() throws CommandProcessingException {
        processor.process(new LineCommand(new Coordinate(1, 1), new Coordinate(1, 10)), null);
    }
}
