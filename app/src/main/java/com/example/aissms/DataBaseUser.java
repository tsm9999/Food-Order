package com.example.aissms;

public class DataBaseUser {
    String name;
    String mail;
    String password;
    String contact;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public DataBaseUser(String name, String mail, String password, String contact) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.contact = contact;
    }
}