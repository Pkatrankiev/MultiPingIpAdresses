package com.example.acer.multipingipadresses.database.models;

public class Object extends Model {
    private String ipAddress;
    private String descrip;
    private String adress;
    private String info;
    private int hostTypeId;
    private int deviceTypeId;

    public Object(){

    }


    public Object(String ipAddress, String descrip, String adress, String info, int hostTypeId, int deviceTypeId){
        this.ipAddress = ipAddress;
        this.descrip = descrip;
        this.adress = adress;
        this.info = info;
        this.hostTypeId = hostTypeId;
        this.deviceTypeId = deviceTypeId;

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

    public void setDescrip(String name) {
        this.descrip = name;
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

    public int getHostTypeId() {
        return hostTypeId;
    }

    public void setHostTypeId(int locationId) {
        this.hostTypeId = locationId;
    }

    public int getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(int eguipmentId) {
        this.deviceTypeId = eguipmentId;
    }


}

