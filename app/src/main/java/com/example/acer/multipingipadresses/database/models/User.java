package com.example.acer.multipingipadresses.database.models;


public class User extends Model {

    private String userType;
    private String userPass;
    private String userName;

    public User(String userName, String userPass, String userType) {
        this.userName = userName;
        this.userPass = userPass;
        this.userType = userType;
    }

    public User(){

    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
