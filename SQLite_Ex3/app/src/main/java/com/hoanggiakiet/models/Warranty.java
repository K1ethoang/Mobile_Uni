package com.hoanggiakiet.models;

public class Warranty {
    int id;
    String name;
    String desc;
    byte[] photo;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Warranty(int id, String name, String desc, byte[] photo) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.photo = photo;
    }
}
