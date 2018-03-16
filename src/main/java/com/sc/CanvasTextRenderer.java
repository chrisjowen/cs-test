package com.sc;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CanvasTextRenderer {

    private String getBound(Canvas canvas) {
        return String.join("", IntStream.range(0, canvas.getWidth()+2)
                .mapToObj(i -> "-")
                .collect(Collectors.<String>toList()));
    }

    public String render(Canvas canvas) {
        String bound = getBound(canvas);
        List<String> lines = IntStream.range(1, canvas.getHeight()+1)
                .mapToObj(y -> {
                    List<String> lineItems = IntStream
                            .range(1, canvas.getWidth()+1)
                            .mapToObj(x -> renderPixel(canvas, new Coordinate(x, y)))
                            .collect(Collectors.<String>toList());

                    return String.format("|%s|", String.join("", lineItems));
                }).collect(Collectors.<String>toList());


        return String.format("%s\n%s\n%s", bound, String.join("\n", lines), bound);
    }

    private String renderPixel(Canvas canvas, Coordinate coordinate) {
        String pixel = canvas.getPixel(coordinate);
        return pixel == null ? " " : pixel;
    }


}
