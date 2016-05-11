package com.example.acer.multipingipadresses.database.models;

import java.util.ArrayList;

/**
 * Created by Acer on 10.5.2016 г..
 */
public class ObjectValues {
    ArrayList<Sample> sampleList;
    ObjectString curentObject;

    public  ObjectValues(){

    }

    public ObjectValues(ObjectString curentObject, ArrayList<Sample> sampleList) {
    }

    public ObjectString getCurentObject() {
        return curentObject;
    }

    public void setCurentObject(ObjectString curentObject) {
        this.curentObject = curentObject;
    }

    public ArrayList<Sample> getSampleList() {
        return sampleList;
    }

    public void setSampleList(ArrayList<Sample> sampleList) {
        this.sampleList = sampleList;
    }
}
