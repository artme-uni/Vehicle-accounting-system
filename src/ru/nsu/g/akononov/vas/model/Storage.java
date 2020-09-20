package ru.nsu.g.akononov.vas.model;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Storage {
    private final File serialisationFile = new File("resource/storage.txt");
    private List<Car> storage = new ArrayList<>();

    public Storage() {
        deserialization();
    }

    public int addDefaultCar() {
        Car car = new Car();
        storage.add(car);
        return getStorageSize() - 1;
    }

    private void serialization() {
        try (Writer writer = new FileWriter(serialisationFile)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writer, storage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData(){
        serialization();
    }

    private void deserialization() {
        try(Reader reader = new FileReader(serialisationFile)){
            ObjectMapper objectMapper = new ObjectMapper();
            storage = objectMapper.readValue(reader, new TypeReference<List<Car>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCar(int carNumber) {
        storage.remove(carNumber);
    }

    public int getStorageSize() {
        return storage.size();
    }

    public List<String> getAttributesNames() {
        return Arrays.asList("Make", "Model", "Body style", "Release year");
    }

    public void setAttributeValue(int carNumber, String attributeName, String attributeValue) {
        switch (attributeName.toLowerCase()) {
            case "make":
                setCarMake(carNumber, attributeValue);
                break;
            case "model":
                setCarModel(carNumber, attributeValue);
                break;
            case "body style":
                setBodyStyle(carNumber, attributeValue);
                break;
            case "release year":
                setReleaseYear(carNumber, Integer.parseInt(attributeValue));
                break;
        }
    }

    public void setCarMake(int carNumber, String makeName) {
        if (makeName.equals("")) {
            throw new IllegalArgumentException("Field \"Make\" cannot be empty");
        }
        storage.get(carNumber).setMake(makeName);
    }

    public void setCarModel(int carNumber, String modelName) {
        if (modelName.equals("")) {
            throw new IllegalArgumentException("Field \"Model\" cannot be empty");
        }
        storage.get(carNumber).setModel(modelName);
    }

    public void setBodyStyle(int carNumber, String bodyStyle) {
        if (bodyStyle.equals("")) {
            throw new IllegalArgumentException("Field \"Body style\" cannot be empty");
        }
        storage.get(carNumber).setBodyStyle(bodyStyle);
    }

    public void setReleaseYear(int carNumber, int releaseYear) {
        int firstProductionYear = 1885;
        int currentYear = LocalDate.now().getYear();
        if (releaseYear < firstProductionYear || releaseYear > currentYear) {
            throw new IllegalArgumentException("Release Year must be from " + firstProductionYear + " to " + currentYear);
        }
        storage.get(carNumber).setReleaseYear(releaseYear);
    }

    public String getAttributeValue(int carNumber, String attributeName) {
        switch (attributeName.toLowerCase()) {
            case "make":
                return getCarMake(carNumber);
            case "model":
                return getCarModel(carNumber);
            case "body style":
                return getCarBodyStyle(carNumber);
            case "release year":
                return String.valueOf(getReleaseYear(carNumber));
        }
        return "";
    }

    public String getCarName(int carNumber) {
        return storage.get(carNumber).getName();
    }

    public String getCarMake(int carNumber) {
        return storage.get(carNumber).getMake();
    }

    public int getReleaseYear(int carNumber) {
        return storage.get(carNumber).getReleaseYear();
    }

    public String getCarModel(int carNumber) {
        return storage.get(carNumber).getModel();
    }

    public String getCarBodyStyle(int carNumber) {
        return storage.get(carNumber).getBodyStyle();
    }

    public String getIndexedName(int carNumber) {
        return "\t(" + carNumber + ") " + storage.get(carNumber).getName();
    }
}
