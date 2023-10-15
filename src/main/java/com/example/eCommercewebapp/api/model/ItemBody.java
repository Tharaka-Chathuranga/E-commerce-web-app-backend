package com.example.eCommercewebapp.api.model;

public class ItemBody {

    private String name;
    private String brand;
    private String price;
    private String discount;
    private String catageory;
    private String details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCatageory() {
        return catageory;
    }

    public void setCatageory(String catageory) {
        this.catageory = catageory;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
