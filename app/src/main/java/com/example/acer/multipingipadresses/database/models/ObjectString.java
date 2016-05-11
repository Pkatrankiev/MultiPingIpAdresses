package com.example.acer.multipingipadresses.database.models;

/**
 * Created by Acer on 10.5.2016 г..
 */
public class ObjectString {
    private int objectId;
    private String ipAddress;
    private String descrip;
    private String adress;
    private String info;
    private String hostType;
    private String deviceType;

    public ObjectString() {
    }

    public ObjectString(int objectId, String ipAddress, String descrip, String adress, String info, String hostType, String deviceType) {
        this.objectId = objectId;
        this.ipAddress = ipAddress;
        this.descrip = descrip;
        this.adress = adress;
        this.info = info;
        this.hostType = hostType;
        this.deviceType = deviceType;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
}
