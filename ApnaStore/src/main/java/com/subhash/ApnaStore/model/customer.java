package com.subhash.ApnaStore.model;

public class customer {
    private String firstName;
    private String lastName;
    private long phone;
    private long total;

    public customer() {

    }

    public customer(String firstName, long phone) {
        this.firstName = firstName;
        this.phone = phone;
    }

    public customer(String firstName, long phone, long total) {
        this.firstName = firstName;
        this.phone = phone;
        this.total = total;
    }

    public customer(String firstName, String lastName, long phone, long total) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.total = total;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhone() {
        return phone;
    }

    public long getTotal() {
        return total;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", total=" + total +
                '}';
    }
}


