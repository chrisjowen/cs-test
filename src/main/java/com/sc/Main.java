package com.sc;

import com.sc.commands.*;
import com.sc.commands.exceptions.CommandParsingException;
import com.sc.processors.*;
import com.sc.processors.exceptions.CommandProcessingException;
import com.sc.renderers.CanvasRenderer;
import com.sc.renderers.PrintStreamCanvasRenderer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Application application = setupApp();
        Scanner scanInput = new Scanner(System.in);
        while (true) {
            try {
                String input = scanInput.nextLine();
                if(exit(input)) break;
                application.processInput(input).render();

            } catch (CommandProcessingException | CommandParsingException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean exit(String input) {
        return input.toLowerCase().equals("exit");
    }

    private static Application setupApp() {
        Painter painter = new Painter(setupCommandProcessors());
        CommandParser parser = new CommandParser();
        CanvasRenderer renderer = new PrintStreamCanvasRenderer(System.out);
        return new Application(painter, parser, renderer);
    }

    private static Map<Class, CommandProcessor> setupCommandProcessors() {
        Map<Class, CommandProcessor> processorMap = new HashMap<>();
        processorMap.put(FillCommand.class, new FillCommandProcessor());
        processorMap.put(LineCommand.class, new LineCommandProcessor());
        processorMap.put(RectangleCommand.class, new RectangleCommandProcessor());
        processorMap.put(CanvasCommand.class, new CanvasCommandProcessor());
        return processorMap;
    }
}
