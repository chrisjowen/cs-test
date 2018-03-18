package com.sc.model;


import com.sc.Painter;

public class PaintContext {
    private Painter painter;
    private Canvas canvas;

    public PaintContext(Painter painter, Canvas canvas) {
        this.painter = painter;
        this.canvas = canvas;
    }

    public Painter getPainter() {
        return painter;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
