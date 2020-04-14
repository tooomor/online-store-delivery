package com.tooomor.online.store.delivery.model;

import java.util.List;

public class Order {
    String orderNumber;
    Client client;
    List<Subcontract> subcontracts;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Subcontract> getSubcontracts() {
        return subcontracts;
    }

    public void setSubcontracts(List<Subcontract> subcontracts) {
        this.subcontracts = subcontracts;
    }

    public Order(String orderNumber, Client client, List<Subcontract> subcontracts) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.subcontracts = subcontracts;
    }
}
