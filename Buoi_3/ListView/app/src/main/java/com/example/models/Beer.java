package com.example.models;

public class Beer {
    int thumb;
    String name;
    double price;

    public Beer(int thumb, String name, double price) {
        this.thumb = thumb;
        this.name = name;
        this.price = price;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
