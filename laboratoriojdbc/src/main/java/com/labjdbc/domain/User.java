package com.labjdbc.domain;

public class User {
    private int iDUser;
    private String userName;
    private String password;

    public User(int iDUser){
        this.iDUser = iDUser;
    }

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public User(int iDUser, String userName, String password){
        this.iDUser = iDUser;
        this.userName = userName;
        this.password = password;
    }

    public int getIDUser(){
        return iDUser;
    }

    public void setIDUser(int iDUser){
        this.iDUser = iDUser;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
