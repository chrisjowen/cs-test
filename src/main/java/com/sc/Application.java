package com.sc;


import com.sc.commands.Command;
import com.sc.commands.CommandParser;
import com.sc.commands.exceptions.CommandException;
import com.sc.processors.CommandProcessor;
import com.sc.processors.exceptions.CommandProcessingException;

import java.io.Console;
import java.util.Scanner;

public  class Application {
    public static void main(String[] args) {

        CommandProcessor processor = null;
        CommandParser parser = new CommandParser();
        TextRenderer renderer = new TextRenderer();

        Scanner scanInput = new Scanner(System.in);


        while (true) {
            String input = scanInput.nextLine();
            try {
                Command command = parser.parse(input);
                if (processor == null) {
                    processor = CommandProcessor.init(command);
                }
                else {
                    processor = processor.then(command);
                }
                System.out.println(processor.renderWith(renderer));

            } catch (CommandProcessingException | CommandException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
