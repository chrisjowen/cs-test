package com.sc;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sc.commands.CanvasCommand;
import com.sc.commands.FillCommand;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.processors.CommandProcessor;
import com.sc.processors.exceptions.CommandProcessingException;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class TextRendererTest {
    private TextRenderer renderer = new TextRenderer();

    @Test
    public void canRenderEmptyCanvas() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4));

        assertEqualsOutputFromResource("empty20x4Canvas.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithLine() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)));

        assertEqualsOutputFromResource("20x4CanvasWithLine.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithLineWillStopRendererPastEndOfCanvas() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(1, 3), new Coordinate(21, 3)));

        assertEqualsOutputFromResource("20x4CanvasWithLineRenderPastEnd.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithLineWithReverseCoordinates() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(6, 2), new Coordinate(1, 2)));

        assertEqualsOutputFromResource("20x4CanvasWithLine.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithHorizontalLine() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)));

        assertEqualsOutputFromResource("20x4CanvasWithHorizontalLine.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithHorizontalLineAndReversedCoordinates() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(6, 4), new Coordinate(6, 3)));

        assertEqualsOutputFromResource("20x4CanvasWithHorizontalLine.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithSeveralLines() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .then(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)));

        assertEqualsOutputFromResource("20x4CanvasWithSeveralLines.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithSquare() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)));

        assertEqualsOutputFromResource("20x4CanvasWithSquare.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithSquareWithReversedCoordinates() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new RectangleCommand(new Coordinate(18, 3), new Coordinate(14, 1)));

        assertEqualsOutputFromResource("20x4CanvasWithSquare.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithRectanglePastEnd() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new RectangleCommand(new Coordinate(4, 0), new Coordinate(21, 3)));

        assertEqualsOutputFromResource("20x4CanvasWithRectangleRenderPastEnd.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithFullFill() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new FillCommand(new Coordinate(4, 1), "o"));

        assertEqualsOutputFromResource("20x4CanvasWithFullFill.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderCanvasWithHalfFill() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(11, 1), new Coordinate(11, 4)))
                .then(new FillCommand(new Coordinate(1, 1), "o"));

        assertEqualsOutputFromResource("20x4CanvasWithHalfFill.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderFullExample() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .then(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)))
                .then(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)))
                .then(new FillCommand(new Coordinate(10, 3), "o"));

        assertEqualsOutputFromResource("20x4FullExample.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderFullExamplePaintingOver() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .then(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)))
                .then(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)))
                .then(new FillCommand(new Coordinate(10, 3), "o"))
                .then(new FillCommand(new Coordinate(10, 3), "g"));

        assertEqualsOutputFromResource("20x4FullExamplePaintingOver.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderFullExamplePaintingOverFillingBoxes() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .then(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)))
                .then(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)))
                .then(new FillCommand(new Coordinate(10, 3), "o"))
                .then(new FillCommand(new Coordinate(10, 3), "g"))
                .then(new FillCommand(new Coordinate(15, 2), "r"))
                .then(new FillCommand(new Coordinate(1, 3), "b"));

        assertEqualsOutputFromResource("20x4FullExamplePaintingOverFillingBoxes.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderFullExamplePaintingOverFillingBoxesAndLines() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new LineCommand(new Coordinate(1, 2), new Coordinate(6, 2)))
                .then(new LineCommand(new Coordinate(6, 3), new Coordinate(6, 4)))
                .then(new RectangleCommand(new Coordinate(14, 1), new Coordinate(18, 3)))
                .then(new FillCommand(new Coordinate(10, 3), "o"))
                .then(new FillCommand(new Coordinate(10, 3), "g"))
                .then(new FillCommand(new Coordinate(15, 2), "r"))
                .then(new FillCommand(new Coordinate(1, 3), "b"))
                .then(new FillCommand(new Coordinate(14, 1), "y"));

        assertEqualsOutputFromResource("20x4FullExamplePaintingOverFillingBoxesAndLines.txt", commandProcessor.renderWith(renderer));
    }

    @Test
    public void canRenderFullFillThenPaintOver() throws IOException, CommandProcessingException {
        CommandProcessor commandProcessor = CommandProcessor.init(new CanvasCommand(20, 4))
                .then(new FillCommand(new Coordinate(4, 1), "o"))
                .then(new RectangleCommand(new Coordinate(11, 1), new Coordinate(7, 3)))
                .then(new FillCommand(new Coordinate(8, 2), "r"));

        assertEqualsOutputFromResource("20x4CanvasWithFullFillAddFilledBox.txt", commandProcessor.renderWith(renderer));
    }

    private void assertEqualsOutputFromResource(String name, String actual) throws IOException {
        URL url = Resources.getResource(name);
        String inputFromFile = Resources.toString(url, Charsets.UTF_8);
        assertEquals(inputFromFile, String.format("%s\n", actual));
    }


}
