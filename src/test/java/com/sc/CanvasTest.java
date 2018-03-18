//package com.sc;
//
//
//import com.sc.commands.LineCommand;
//import com.sc.processors.exceptions.CommandProcessingException;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//
//public class CanvasTest {
//    @Test()
//    public void shouldCreateNewEmptyCanvas() {
//        CommandProcessor canvas = new CommandProcessor(2, 2);
//        assertEquals(canvas.getPixel(0,0), null);
//        assertEquals(canvas.getPixel(0,1), null);
//        assertEquals(canvas.getPixel(1, 0), null);
//        assertEquals(canvas.getPixel(1,1), null);
//    }
//
//    @Test()
//    public void shouldCreateCanvasWithHorizontalLine() throws CommandProcessingException {
//        CommandProcessor canvas = new CommandProcessor(5, 3)
//                    .paint(new LineCommand(new Coordinate(2, 2), new Coordinate(4, 2)));
//
//        assertEquals(canvas.getPixel(0,0), null);
//        assertEquals(canvas.getPixel(0,1), null);
//        assertEquals(canvas.getPixel(0,2), null);
//        assertEquals(canvas.getPixel(1,0), null);
//        assertEquals(canvas.getPixel(1,1), "x");
//        assertEquals(canvas.getPixel(1,2), null);
//        assertEquals(canvas.getPixel(2,0), null);
//        assertEquals(canvas.getPixel(2,1), "x");
//        assertEquals(canvas.getPixel(2,2), null);
//        assertEquals(canvas.getPixel(3,0), null);
//        assertEquals(canvas.getPixel(3,1), "x");
//        assertEquals(canvas.getPixel(3,2), null);
//        assertEquals(canvas.getPixel(4,0), null);
//        assertEquals(canvas.getPixel(4,1), null);
//        assertEquals(canvas.getPixel(4,2), null);
//    }
//}
