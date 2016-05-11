package com.example.acer.multipingipadresses.database.models;

public class Sample extends Model {

    private int objectId;
    private int measurementTypeId;
    private int measurementValue;
    private String timeMeasurement;

    public Sample(int objectId, int measurementTypeId, int measurementValue, String timeMeasurement) {
        this.objectId = objectId;
        this.measurementTypeId = measurementTypeId;
        this.measurementValue = measurementValue;
        this.timeMeasurement = timeMeasurement;
    }

    public Sample() {

    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getMeasurementTypeId() {
        return measurementTypeId;
    }

    public void setMeasurementTypeId(int measurementTypeId) {
        this.measurementTypeId = measurementTypeId;
    }

    public int getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(int measurementValue) {
        this.measurementValue = measurementValue;
    }


    public String getTimeMeasurement() {
        return timeMeasurement;
    }

    public void setTimeMeasurement(String timeMeasurement) {
        this.timeMeasurement = timeMeasurement;
    }
}
