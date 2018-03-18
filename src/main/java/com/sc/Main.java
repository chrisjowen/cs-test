package com.sc;

import com.sc.commands.*;
import com.sc.commands.exceptions.CommandParsingException;
import com.sc.processors.*;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.renderers.PrintStreamCanvasRenderer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Map<Class, CommandProcessor> processorMap = new HashMap<>();
        processorMap.put(FillCommand.class, new FillCommandProcessor());
        processorMap.put(LineCommand.class, new LineCommandProcessor());
        processorMap.put(RectangleCommand.class, new RectangleCommandProcessor());
        processorMap.put(CanvasCommand.class, new CanvasCommandProcessor());

        Painter painter = new Painter(processorMap);
        CommandParser parser = new CommandParser();
        CanvasRenderer renderer = new PrintStreamCanvasRenderer(System.out);
        Scanner scanInput = new Scanner(System.in);
        Application application = new Application(painter, parser, renderer);

        while (true) {
            try {
                application.processInput(scanInput.nextLine())
                           .render();

            } catch (CommandProcessingException | CommandParsingException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
