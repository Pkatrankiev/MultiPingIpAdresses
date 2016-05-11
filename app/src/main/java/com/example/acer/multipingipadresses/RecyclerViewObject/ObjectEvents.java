package com.example.acer.multipingipadresses.RecyclerViewObject;

/**
 * Created by Acer on 7.5.2016 г..
 */
public class ObjectEvents {

    private int objectId;
    private String objectIp;
    private String descrip;
    private String address;
    private String info;
    private String hostType;
    private String deviceType;

    public ObjectEvents(int objectId, String objectIp, String descrip, String address, String info, String hostType, String deviceType) {
        this.objectId = objectId;
        this.objectIp = objectIp;
        this.descrip = descrip;
        this.address = address;
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
}
