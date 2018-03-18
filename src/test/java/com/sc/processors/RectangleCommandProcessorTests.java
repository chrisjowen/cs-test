package com.sc.processors;


import com.sc.Painter;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import com.sc.model.PaintContext;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.MissingContextWhenProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class RectangleCommandProcessorTests {
    RectangleCommandProcessor processor = new RectangleCommandProcessor();
    Canvas mockedCanvas;
    Painter mockedPainter;
    PaintContext context;

    @Before
    public void before() {
        this.mockedCanvas = mock(Canvas.class);
        this.mockedPainter = mock(Painter.class);
        this.context = new PaintContext(mockedPainter, this.mockedCanvas);
    }
    @After
    public void after() {
        verifyNoMoreInteractions(this.mockedCanvas);
        verifyNoMoreInteractions(this.mockedPainter);
    }

    @Test
    public void shouldDivertToLineProcessorToProcessRectangle() throws CommandProcessingException {
        when(this.mockedPainter.paint(any())).thenReturn(this.mockedPainter);

        processor.process(new RectangleCommand(new Coordinate(1, 1), new Coordinate(3, 3)), context);

        verify(this.mockedPainter).paint(Matchers.eq(new LineCommand(new Coordinate(1, 1), new Coordinate(3, 1))));
        verify(this.mockedPainter).paint(Matchers.eq(new LineCommand(new Coordinate(3, 1), new Coordinate(3, 3))));
        verify(this.mockedPainter).paint(Matchers.eq(new LineCommand(new Coordinate(3, 3), new Coordinate(1, 3))));
        verify(this.mockedPainter).paint(Matchers.eq(new LineCommand(new Coordinate(1, 3), new Coordinate(1, 1))));
        verify(this.mockedPainter).getCanvas();
    }

    @Test(expected = MissingContextWhenProcessingException.class)
    public void shouldFailToProcessCommandWithoutContext() throws CommandProcessingException {
        processor.process(new RectangleCommand(new Coordinate(1, 1), new Coordinate(3, 3)), null);
    }

}
