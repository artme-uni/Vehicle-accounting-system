package ru.nsu.g.akononov.vas;

import ru.nsu.g.akononov.vas.controller.CLIController;
import ru.nsu.g.akononov.vas.controller.GUIController;
import ru.nsu.g.akononov.vas.model.Storage;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            if (args.length == 1) {
                if (args[0].toLowerCase().equals("-gui")) {
                    createController(true);
                } else if (args[0].toLowerCase().equals("-cli")) {
                    createController(false);
                } else {
                    request(in);
                }
            } else {
                request(in);
            }
        }
    }

    private static void printExpectedKeys() {
        System.out.println("Expected keys:\n" +
                "\t\"-cli\" to use Command-line interface\n" +
                "\t\"-gui\" to use Graphical user interface");
    }

    private static void request(Scanner in) {
        printExpectedKeys();
        System.out.println("Do you want to us Graphical user interface?");
        createController(requestIsGui(in).equals("yes"));
    }

    private static String requestIsGui(Scanner in) {
        System.out.print("Enter yes/no : ");
        String response;
            response = in.nextLine();
            if (!response.toLowerCase().equals("yes") && !response.toLowerCase().equals("no")) {
                response = requestIsGui(in);
        }
        return response;
    }

    private static void createController(boolean isGUI) {
        Storage storage = new Storage();

        if (isGUI) {
            GUIController controller = new GUIController(storage);
        } else {
            CLIController cliController = new CLIController(storage);
        }
    }
}
