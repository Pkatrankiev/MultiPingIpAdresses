package com.example.acer.multipingipadresses.database.models;

public class Device extends Model {

    private String deviceType;
 public Device (String deviceType){
     this.deviceType = deviceType;
 }
    public Device(){

    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}

