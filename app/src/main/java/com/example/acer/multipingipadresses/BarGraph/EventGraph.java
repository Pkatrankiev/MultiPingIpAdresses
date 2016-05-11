package com.example.acer.multipingipadresses.BarGraph;

import java.util.ArrayList;

/**
 * Created by Acer on 5.5.2016 г..
 */
public class EventGraph {


    private String objectIp;
    private String descrip;
    private String address;
    private String info;
    private String hostType;
    private String deviceType;
    private ArrayList<Integer> imgSpeed;
    private ArrayList<String> timeMeasurement;


    public EventGraph(String objectIp, String descrip, String address, String info, String hostType, String deviceType, ArrayList<Integer> imgSpeed, ArrayList<String> timeMeasurement) {


        this.objectIp = objectIp;
        this.descrip = descrip;
        this.address = address;
        this.info = info;
        this.hostType = hostType;
        this.deviceType = deviceType;
        this.imgSpeed = imgSpeed;
        this.timeMeasurement = timeMeasurement;
    }

    public String getObjectIp() {
        return objectIp;
    }

    public void setObjectIp(String objectIp) {
        this.objectIp = objectIp;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHostType() {
        return hostType;
    }

    public void setHostType(String hostType) {
        this.hostType = hostType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public ArrayList<Integer> getImgSpeed() {
        return imgSpeed;
    }

    public void setImgSpeed(ArrayList<Integer> imgSpeed) {
        this.imgSpeed = imgSpeed;
    }

    public ArrayList<String> getTimeMeasurement() {
        return timeMeasurement;
    }

    public void setTimeMeasurement(ArrayList<String> timeMeasurement) {
        this.timeMeasurement = timeMeasurement;
    }
}

