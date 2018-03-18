package com.sc;


import com.sc.commands.CanvasCommand;
import com.sc.commands.FillCommand;
import com.sc.model.Coordinate;
import com.sc.processors.CommandProcessor;
import com.sc.processors.FillCommandProcessor;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.processors.exceptions.MissingCommandProcessorException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PainterTests {

    private FillCommandProcessor processor;
    private Painter painter;

    @Before
    public void before() {
        this.processor = mock(FillCommandProcessor.class);
        Map<Class, CommandProcessor> processorMap = new HashMap<>();
        processorMap.put(FillCommand.class, processor);
        this.painter = new Painter(processorMap);

    }

    @Test
    public void shouldPaintWithAppropriateProcessor() throws CommandProcessingException {
        FillCommand command = new FillCommand(new Coordinate(1, 1), 'b');
        painter.paint(command);

        verify(processor).process(eq(command), any());
    }

    @Test(expected = MissingCommandProcessorException.class)
    public void shouldErrorIfProcessorMissing() throws CommandProcessingException {
        painter.paint(new CanvasCommand(1,1));

    }

}
