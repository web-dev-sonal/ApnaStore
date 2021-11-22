package com.subhash.ApnaStore.model;

public class product {
    private int id;
    private long price;

    public product() {
    }

    public product(int id, long price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
