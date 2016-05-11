package com.example.acer.multipingipadresses.RecyclerView;

/**
 * Created by Acer on 2.5.2016 г..
 */
public class Events {

    private int objectId;
    private String descrip;
    private String host;
    private String address;
    private String device;
    private int imgSpeed1;
    private int imgSpeed2;
    private int imgSpeed3;

    public Events(int objectId, String descrip, String host, String device, String address, int imgSpeed1, int imgSpeed2, int imgSpeed3) {
        this.objectId = objectId;
        this.descrip = descrip;
        this.host = host;
        this.address = address;
        this.device = device;
        this.imgSpeed1 = imgSpeed1;
        this.imgSpeed2 = imgSpeed2;
        this.imgSpeed3 = imgSpeed3;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getImgSpeed1() {
        return imgSpeed1;
    }

    public void setImgSpeed1(int imgSpeed1) {
        this.imgSpeed1 = imgSpeed1;
    }

    public int getImgSpeed2() {
        return imgSpeed2;
    }

    public void setImgSpeed2(int imgSpeed2) {
        this.imgSpeed2 = imgSpeed2;
    }

    public int getImgSpeed3() {
        return imgSpeed3;
    }

    public void setImgSpeed3(int imgSpeed3) {
        this.imgSpeed3 = imgSpeed3;
    }
}
