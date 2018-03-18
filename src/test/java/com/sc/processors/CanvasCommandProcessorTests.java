package com.sc.processors;


import com.sc.commands.CanvasCommand;
import com.sc.model.Canvas;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CanvasCommandProcessorTests {
    CanvasCommandProcessor processor = new CanvasCommandProcessor();

    @Test
    public void shouldCreateNewCanvas() {
        Canvas canvas = processor.process(new CanvasCommand(10, 5), null);
        assertEquals(canvas.getWidth(), 10) ;
        assertEquals(canvas.getHeight(), 5) ;
    }
}
