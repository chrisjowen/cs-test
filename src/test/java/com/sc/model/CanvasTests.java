package com.sc.model;


import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

public class CanvasTests {
    @Test()
    public void shouldAddAndGetPixel() {
        Canvas canvas = new Canvas(2, 2);
        canvas.addPixel(new Coordinate(1,1), 'x');
        assertEquals("x", canvas.getPixel(new Coordinate(1, 1)).toString());
        assertNull(canvas.getPixel(new Coordinate(2,2)));
    }

    @Test()
    public void shouldDetectOutOfBounds() {
        Canvas canvas = new Canvas(2, 2);
        canvas.addPixel(new Coordinate(1, 1), 'x');
        assertTrue(canvas.isInBounds(new Coordinate(1, 1)));
        assertFalse(canvas.isInBounds(new Coordinate(3, 1)));
        assertFalse(canvas.isInBounds(new Coordinate(1, 3)));
    }


    @Test()
    public void shouldReturnNullPixelIfOOB() {
        Canvas canvas = new Canvas(1, 1);
        canvas.addPixel(new Coordinate(1, 1), 'x');
        assertNull(canvas.getPixel(new Coordinate(1, 2)));
        assertNull(canvas.getPixel(new Coordinate(2, 1)));
    }
}
