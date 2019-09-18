package com.example.aissms;

public class User {
    int amount;
    String id;
    public User(int amount, String id, String items) {
        this.amount = amount;
        this.id = id;
        this.items = items;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getItems() {
        return items;
    }
    public void setItems(String items) {
        this.items = items;
    }
    String items;
}