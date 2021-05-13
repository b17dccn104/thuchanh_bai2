package com.example.buihoangdat_bai2;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String name, date;
    private int amount;
    private double price;

    public Order(int id, String name, String date, int amount, double price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order(String name, String date, int amount, double price) {
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.price = price;
    }

    public Order() {
    }
}
