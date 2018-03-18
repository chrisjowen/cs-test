package com.sc.renderers;

import com.sc.model.Canvas;

import java.io.PrintStream;


public class PrintStreamCanvasRenderer extends BaseTextCanvasRenderer {
    private PrintStream stream;

    public PrintStreamCanvasRenderer(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void render(Canvas canvas) {
        this.stream.println(this.renderText(canvas));
    }
}
