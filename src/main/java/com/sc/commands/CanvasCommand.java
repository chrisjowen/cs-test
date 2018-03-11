package com.sc.commands;


public class CanvasCommand implements Command {
    private int width;

    public CanvasCommand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
