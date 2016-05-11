package com.example.acer.multipingipadresses.database.models;

public class Measurement extends Model {

    private String measurementType;

public Measurement(String measurementType){
    this.measurementType = measurementType;
}
    public Measurement(){

    }
    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }
}
