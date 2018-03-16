package com.sc;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sc.commands.FillCommand;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.processors.exceptions.CommandProcessingException;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class CanvasTextRendererTest {
    private CanvasTextRenderer renderer = new CanvasTextRenderer();

    @Test
    public void canRenderEmptyCanvas() throws IOException {
        Canvas canvas = new Canvas(20, 4);

        assertEqualsOutputFromResource("empty20x4Canvas.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithLine() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)));

        assertEqualsOutputFromResource("20x4CanvasWithLine.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithLineWillStopRendererPastEndOfCanvas() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(1, 3), new Coordinate(21, 3)));

        assertEqualsOutputFromResource("20x4CanvasWithLineRenderPastEnd.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithLineWithReverseCoordinates() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(6, 2), new Coordinate(1, 2)));

        assertEqualsOutputFromResource("20x4CanvasWithLine.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithHorizontalLine() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)));

        assertEqualsOutputFromResource("20x4CanvasWithHorizontalLine.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithHorizontalLineAndReversedCoordinates() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(6, 4), new Coordinate(6, 3)));

        assertEqualsOutputFromResource("20x4CanvasWithHorizontalLine.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithSeveralLines() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .processCommand(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)));

        assertEqualsOutputFromResource("20x4CanvasWithSeveralLines.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithSquare() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)));

        assertEqualsOutputFromResource("20x4CanvasWithSquare.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithSquareWithReversedCoordinates() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new RectangleCommand(new Coordinate(18, 3), new Coordinate(14, 1)));

        assertEqualsOutputFromResource("20x4CanvasWithSquare.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithRectanglePastEnd() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new RectangleCommand(new Coordinate(4, 0), new Coordinate(21, 3)));

        assertEqualsOutputFromResource("20x4CanvasWithRectangleRenderPastEnd.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithFullFill() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new FillCommand(new Coordinate(4, 1), "o"));

        assertEqualsOutputFromResource("20x4CanvasWithFullFill.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderCanvasWithHalfFill() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(11, 1), new Coordinate(11, 4)))
                .processCommand(new FillCommand(new Coordinate(1, 1), "o"));

        assertEqualsOutputFromResource("20x4CanvasWithHalfFill.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderFullExample() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .processCommand(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)))
                .processCommand(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)))
                .processCommand(new FillCommand(new Coordinate(10, 3), "o"));

        assertEqualsOutputFromResource("20x4FullExample.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderFullExamplePaintingOver() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .processCommand(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)))
                .processCommand(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)))
                .processCommand(new FillCommand(new Coordinate(10, 3), "o"))
                .processCommand(new FillCommand(new Coordinate(10, 3), "g"));

        assertEqualsOutputFromResource("20x4FullExamplePaintingOver.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderFullExamplePaintingOverFillingBoxes() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .processCommand(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)))
                .processCommand(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)))
                .processCommand(new FillCommand(new Coordinate(10, 3), "o"))
                .processCommand(new FillCommand(new Coordinate(10, 3), "g"))
                .processCommand(new FillCommand(new Coordinate(15, 2), "r"))
                .processCommand(new FillCommand(new Coordinate(1, 3), "b"));

        assertEqualsOutputFromResource("20x4FullExamplePaintingOverFillingBoxes.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderFullExamplePaintingOverFillingBoxesAndLines() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .processCommand(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)))
                .processCommand(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)))
                .processCommand(new FillCommand(new Coordinate(10, 3), "o"))
                .processCommand(new FillCommand(new Coordinate(10, 3), "g"))
                .processCommand(new FillCommand(new Coordinate(15, 2), "r"))
                .processCommand(new FillCommand(new Coordinate(1, 3), "b"))
                .processCommand(new FillCommand(new Coordinate(14, 1), "y"));

        assertEqualsOutputFromResource("20x4FullExamplePaintingOverFillingBoxesAndLines.txt", renderer.render(canvas));
    }

    @Test
    public void canRenderFullFillThenPaintOver() throws IOException, CommandProcessingException {
        Canvas canvas = new Canvas(20, 4)
                .processCommand(new FillCommand(new Coordinate(4, 1), "o"))
                .processCommand(new RectangleCommand(new Coordinate(11, 1), new Coordinate(7, 3)))
                .processCommand(new FillCommand(new Coordinate(8, 2), "r"))
                ;

        assertEqualsOutputFromResource("20x4CanvasWithFullFillAddFilledBox.txt", renderer.render(canvas));
    }

    private void assertEqualsOutputFromResource(String name, String actual) throws IOException {
        URL url = Resources.getResource(name);
        String inputFromFile = Resources.toString(url, Charsets.UTF_8);
        assertEquals(inputFromFile, String.format("%s\n", actual));
    }


}
