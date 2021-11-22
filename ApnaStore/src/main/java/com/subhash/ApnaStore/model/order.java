package com.subhash.ApnaStore.model;


public class order {
    private int qty;
    private int productId;
    private long customerId;

    public order() {
    }

    public order(int qty, int productId, long customerId) {
        this.qty = qty;
        this.productId = productId;
        this.customerId = customerId;
    }



    public int getQty() {
        return qty;
    }

    public int getProductId() {
        return productId;
    }

    public long getCustomerId() {
        return customerId;
    }



    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "order{" +
                " qty=" + qty +
                ", productId=" + productId +
                ", customerId=" + customerId +
                '}';
    }
}
