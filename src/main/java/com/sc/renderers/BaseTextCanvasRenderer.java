package com.sc.renderers;


import com.sc.model.Canvas;
import com.sc.model.Coordinate;
import com.sc.utils.ListUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseTextCanvasRenderer implements CanvasRenderer {

    @Override
    public abstract void render(Canvas canvas);

    protected String renderText(Canvas canvas) {
        String bound = getBound(canvas);
        List<String> lines = IntStream.range(1, canvas.getHeight()+1)
                .mapToObj(y -> renderLine(canvas, y))
                .collect(Collectors.<String>toList());

        return String.format("%s\n%s\n%s", bound, String.join("\n", lines), bound);
    }

    private String getBound(Canvas canvas) {
        return String.join("", IntStream.range(0, canvas.getWidth() + 2)
                .mapToObj(i -> "-")
                .collect(Collectors.<String>toList()));
    }

    private String renderLine(Canvas canvas, int y) {
        List<Character> lineItems = IntStream
                .range(1, canvas.getWidth() + 1)
                .mapToObj(x -> renderPixel(canvas, new Coordinate(x, y)))
                .collect(Collectors.<Character>toList());
        return String.format("|%s|", ListUtils.joinChars(lineItems));
    }

    private Character renderPixel(Canvas canvas, Coordinate coordinate) {
        Character pixel = canvas.getPixel(coordinate);
        return pixel == null ? ' '  : pixel;
    }

}
