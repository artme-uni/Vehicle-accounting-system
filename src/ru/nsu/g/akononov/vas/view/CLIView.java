package ru.nsu.g.akononov.vas.view;

import java.util.List;

public class CLIView {

    public void printCarDetails(String bodyStyle, int releaseYear) {
        System.out.println("\tBody Style: " + bodyStyle);
        System.out.println("\tRelease Year: " + releaseYear);
    }

    public void printName(String name) {
        System.out.println(name);
    }

    public void printInitMsg() {
        System.out.println("\n\nWelcome to the Vehicle Accounting System!\n" +
                "Enter \"help\" to view available commands");
        printSpace();
    }

    public void printListHeader() {
        System.out.println("Car list:");
    }

    public void printHelp(int storageSize) {
        System.out.println("Enter:\n" +
                "\t \"view\"\t\t\t- to view all cars\n" +
                "\t \"add\"\t\t\t- to add new records\n" +
                "\t \"edit [N]\"\t\t- to edit the property\n" +
                "\t \"read [N]\"\t\t- to read more about the car\n" +
                "\t \"remove [N]\"\t- to remove the record\n" +
                "\t \"save\"\t\t\t- to save all changes\n" +
                "\t \"exit\"\t\t\t- to shut down\n" +
                "\t Where N is the car number from 0 to " + (storageSize - 1));
        printSpace();
    }

    public void printCarInfo(String carName, String bodyStyle, int releaseYear) {
        printName(carName);
        printCarDetails(bodyStyle, releaseYear);
        System.out.println();
    }

    public void printSpace() {
        System.out.println();
    }

    public void printIncorrectInputErr() {
        System.out.println("Incorrect input, please try again.");
    }

    public void printHint(String message) {
        System.out.println("Hint: " + message);
    }

    public void printConfirmation() {
        System.out.println("Operation successfully COMPLETED.");
        printSpace();
    }

    public void request(String propertyName) {
        System.out.print("Enter " + propertyName + ": ");
    }

    public void requestPropertyNumber(List<String> attributeNames) {
        System.out.println("What do you want to edit?");
        int i = 1;
        for (String name : attributeNames) {
            System.out.println("\t(" + i + ") " + name);
            i++;
        }
        System.out.print("Property number: ");
    }
}
