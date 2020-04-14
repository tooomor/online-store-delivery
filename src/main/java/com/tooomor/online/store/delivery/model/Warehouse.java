package com.tooomor.online.store.delivery.model;

public class Warehouse {
    String code;
    String fullName;
    Address address;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Warehouse(String code, String fullName, Address address) {
        this.code = code;
        this.fullName = fullName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "code='" + code + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address=" + address.toString() +
                '}';
    }
}
