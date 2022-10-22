package com.example.miniproject;

public class ModelUser {
    public String userName;
    public String uPass;
    public String phone;
    public String email;

    public ModelUser(){    }
    public ModelUser(String userName,String uPass,String phone, String email) {

        this.userName=userName;
        this.email =email;
        this.phone=phone;
        this.uPass = uPass;

    }

}
