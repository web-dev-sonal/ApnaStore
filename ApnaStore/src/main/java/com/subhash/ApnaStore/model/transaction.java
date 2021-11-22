package com.subhash.ApnaStore.model;
import java.sql.Timestamp;

public class transaction {
    private int id;
    private int amount;
    private long customerId;

    public transaction() {
    }

    public transaction(int amount, long customerId) {
        this.amount = amount;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }


}
