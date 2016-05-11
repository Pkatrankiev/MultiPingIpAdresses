package com.example.acer.multipingipadresses.database.models;

public class Host extends Model {


    private String hostType;

    public Host(String hostType) {
        this.hostType = hostType;
    }
    public Host(){

    }
    public String getHostType() {
        return hostType;
    }

    public void setHostType(String hostType) {
        this.hostType = hostType;
    }
}
