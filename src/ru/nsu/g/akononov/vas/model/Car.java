package ru.nsu.g.akononov.vas.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect
public class Car {
    private String make;
    private String model;
    private String bodyStyle;
    private int releaseYear;

    public Car(String make, String model, String bodyStyle, int releaseYear) {
        this.make = make;
        this.model = model;
        this.bodyStyle = bodyStyle;
        setReleaseYear(releaseYear);
    }

    public Car(){
        this.make = "";
        this.model = "";
        this.bodyStyle = "";
        this.releaseYear = 0;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    @JsonIgnore
    public String getName(){
        return  make + " - " + model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
