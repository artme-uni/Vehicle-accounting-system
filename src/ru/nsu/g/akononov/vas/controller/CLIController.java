package ru.nsu.g.akononov.vas.controller;

import ru.nsu.g.akononov.vas.view.CLIView;
import ru.nsu.g.akononov.vas.model.Storage;

import java.util.List;
import java.util.Scanner;

public class CLIController {
    private final Storage storage;
    private final CLIView view;
    private Scanner in;
    private boolean isWorking;

    public CLIController(Storage storage) {
        this.storage = storage;
        this.view = new CLIView();
        start();
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            in = scanner;
            isWorking = true;
            view.printInitMsg();

            while (isWorking) {
                try {
                    String line = in.nextLine();
                    String[] commands = line.split("\\s");
                    processCmd(commands);
                } catch (RuntimeException e) {
                    view.printIncorrectInputErr();
                    view.printHelp(storage.getStorageSize());
                }
            }
        }
    }

    private void processCmd(String[] commands) {
        if (commands.length == 2) {
            int carNumber = Integer.parseInt(commands[1]);
            if (carNumber >= storage.getStorageSize() || carNumber < 0) {
                throw new IllegalArgumentException();
            }
            processSpecialCmd(commands[0], carNumber);
        } else if (commands.length == 1) {
            processGeneralCmd(commands[0]);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void processGeneralCmd(String cmdName) {
        switch (cmdName) {
            case "view":
                showList();
                break;
            case "help":
                view.printHelp(storage.getStorageSize());
                break;
            case "add":
                addCar();
                break;
            case "exit":
                storage.saveData();
                isWorking = false;
                break;
            case "save":
                storage.saveData();
                view.printConfirmation();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void processSpecialCmd(String cmdName, int carNumber) {
        switch (cmdName) {
            case "edit":
                editCar(carNumber);
                break;
            case "read":
                view.printCarInfo(storage.getIndexedName(carNumber),
                        storage.getCarBodyStyle(carNumber), storage.getReleaseYear(carNumber));
                break;
            case "remove":
                storage.removeCar(carNumber);
                view.printConfirmation();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void showList() {
        view.printListHeader();
        for (int i = 0; i < storage.getStorageSize(); i++) {
            view.printName(storage.getIndexedName(i));
        }
        view.printSpace();
    }

    private void addCar() {
        int index = storage.addDefaultCar();
        for(String name : storage.getAttributesNames()){
            setAttribute(index, name);
        }
        view.printConfirmation();
    }

    private void setAttribute(int carNumber, String attributeName){
        try{
            String attributeValue = scanValueOf(attributeName);
            storage.setAttributeValue(carNumber, attributeName, attributeValue);
        }catch (RuntimeException exception){
            view.printIncorrectInputErr();
            view.printHint(exception.getMessage());
            setAttribute(carNumber, attributeName);
        }
    }

    private void editCar(int carNumber) {
        List<String> attributeNames = storage.getAttributesNames();
        view.requestPropertyNumber(attributeNames);
        int propertyNumber = scanIntFromInterval(1, 4);
        String attributeName = attributeNames.get(propertyNumber-1);
        setAttribute(carNumber, attributeName);
        view.printConfirmation();
    }

    private String scanValueOf(String attributeName){
        view.request(attributeName);
        return in.nextLine();
    }

    private int scanIntFromInterval(int includedBegin, int includedEnd) {
        int result = scanIntValue();
        if (result > includedEnd || result < includedBegin) {
            view.printIncorrectInputErr();
            view.printHint("Value must be from " + includedBegin + " to " + includedEnd);
            view.request("");
            result = scanIntFromInterval(includedBegin, includedEnd);
        }
        return result;
    }

    private int scanIntValue() {
        int result;
        try {
            result = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            view.printIncorrectInputErr();
            view.request("");
            result = scanIntValue();
        }
        return result;
    }
}