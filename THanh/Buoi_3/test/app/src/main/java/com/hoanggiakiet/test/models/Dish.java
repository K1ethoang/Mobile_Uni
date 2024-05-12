package com.hoanggiakiet.test.models;

public class Dish {
    private int dishId;
    private String dishName;
    private String dishDesc;
    private Double dishPrice;
    private byte[] dishImage;
    private int categoryId;

    public Dish(int dishId, String dishName, String dishDesc, Double dishPrice, byte[] dishImage, int categoryId) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishDesc = dishDesc;
        this.dishPrice = dishPrice;
        this.dishImage = dishImage;
        this.categoryId = categoryId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishDesc() {
        return dishDesc;
    }

    public void setDishDesc(String dishDesc) {
        this.dishDesc = dishDesc;
    }

    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public byte[] getDishImage() {
        return dishImage;
    }

    public void setDishImage(byte[] dishImage) {
        this.dishImage = dishImage;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
