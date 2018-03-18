package com.sc.integration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sc.Application;
import com.sc.CommandParser;
import com.sc.CommandProcessor;
import com.sc.Painter;
import com.sc.commands.CanvasCommand;
import com.sc.commands.FillCommand;
import com.sc.commands.LineCommand;
import com.sc.commands.RectangleCommand;
import com.sc.commands.exceptions.CommandParsingException;
import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import com.sc.processors.*;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.renderers.BaseTextCanvasRenderer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ApplicationIntegrationTest {
    private Painter painter;
    private Application application;
    private TestTextCanvasRenderer renderer ;
    private CommandParser parser;
    private Map<Class, CommandProcessor> processors;

    @Before
    public void beforeEach() {
        processors = new HashMap<Class, CommandProcessor>() {{
            put(FillCommand.class, new FillCommandProcessor());
            put(LineCommand.class, new LineCommandProcessor());
            put(RectangleCommand.class, new RectangleCommandProcessor());
            put(CanvasCommand.class, new CanvasCommandProcessor());
        }};
        parser = new CommandParser();
        renderer = new TestTextCanvasRenderer();
        painter = new Painter(processors);
        application = new Application(painter, parser, renderer);
    }

    @Test
    public void canRenderEmptyCanvas() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4").render();

        assertEqualsOutputFromResource("empty20x4Canvas.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithLine() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 1 2 6 2")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithLine.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithLineWillStopRendererPastEndOfCanvas() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 1 3 21 3")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithLineRenderPastEnd.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithLineWithReverseCoordinates() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 6 2 1 2")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithLine.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithHorizontalLine() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 6 3 6 4")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithHorizontalLine.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithHorizontalLineAndReversedCoordinates() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 6 4 6 3")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithHorizontalLine.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithSeveralLines() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 1 2 6 2")
                .processInput("L 6 3 6 4")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithSeveralLines.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithSquare() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("R 14 1 18 3")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithSquare.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithSquareWithReversedCoordinates() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("R 18 3 14 1")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithSquare.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithRectanglePastEnd() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("R 4 0 21 3")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithRectangleRenderPastEnd.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithFullFill() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("B 4 1 o")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithFullFill.txt", renderer.getOutput());
    }

    @Test
    public void canRenderCanvasWithHalfFill() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 11 1 11 4")
                .processInput("B 1 1 o")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithHalfFill.txt", renderer.getOutput());
    }

    @Test
    public void canRenderFullExample() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 1 2 6 2")
                .processInput("L 6 3 6 4")
                .processInput("R 14 1 18 3")
                .processInput("B 10 3 o")
                .render();

        assertEqualsOutputFromResource("20x4FullExample.txt", renderer.getOutput());
    }

    @Test
    public void canRenderFullExamplePaintingOver() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 1 2 6 2")
                .processInput("L 6 3 6 4")
                .processInput("R 14 1 18 3")
                .processInput("B 10 3 o")
                .processInput("B 10 3 g")
                .render();

        assertEqualsOutputFromResource("20x4FullExamplePaintingOver.txt", renderer.getOutput());
    }

    @Test
    public void canRenderFullExamplePaintingOverFillingBoxes() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 1 2 6 2")
                .processInput("L 6 3 6 4")
                .processInput("R 14 1 18 3")
                .processInput("B 10 3 o")
                .processInput("B 10 3 g")
                .processInput("B 15 2 r")
                .processInput("B 1 3 b")
                .render();

        assertEqualsOutputFromResource("20x4FullExamplePaintingOverFillingBoxes.txt", renderer.getOutput());
    }

    @Test
    public void canRenderFullExamplePaintingOverFillingBoxesAndLines() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("L 1 2 6 2")
                .processInput("L 6 3 6 4")
                .processInput("R 14 1 18 3")
                .processInput("B 10 3 o")
                .processInput("B 10 3 g")
                .processInput("B 15 2 r")
                .processInput("B 1 3 b")
                .processInput("B 14 1 y")
                .render();

        assertEqualsOutputFromResource("20x4FullExamplePaintingOverFillingBoxesAndLines.txt", renderer.getOutput());
    }

    @Test
    public void canRenderFullFillThenPaintOver() throws  CommandProcessingException, IOException, CommandParsingException {
        application.processInput("C 20 4")
                .processInput("B 4 1 o")
                .processInput("R 11 1 7 3")
                .processInput("B 8 2 r")
                .render();

        assertEqualsOutputFromResource("20x4CanvasWithFullFillAddFilledBox.txt", renderer.getOutput());
    }

    private void assertEqualsOutputFromResource(String name, String actual) throws IOException {
        URL url = Resources.getResource(name);
        String inputFromFile = Resources.toString(url, Charsets.UTF_8);
        assertEquals(inputFromFile, String.format("%s\n", actual));
    }

    private class TestTextCanvasRenderer extends BaseTextCanvasRenderer {
        private String output;

        @Override
        public void render(Canvas canvas) {
            this.output = renderText(canvas);
        }

        public String getOutput() {
            return output;
        }

    }
}
