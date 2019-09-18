package com.example.bottombar_navigation_with_fragment;

public class ListItem {

    private String items;
    private String order_id;
    private String  payment_method;
    private int total_cost;
    private String username;
    private String status;
    public ListItem(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getItems() {
        return items;
    }
    public void setItems(String items) {
        this.items = items;
    }
    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    public String getPayment_method() {
        return payment_method;
    }
    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
    public int getTotal_cost() {
        return total_cost;
    }
    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public ListItem(String username, String order_id, String items, String payment_method, int total_cost, String status) {
        this.items = items;
        this.order_id = order_id;
        this.payment_method = payment_method;
        this.total_cost = total_cost;
        this.username = username;
        this.status=status;
    }
}
