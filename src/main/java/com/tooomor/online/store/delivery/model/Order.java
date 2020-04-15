package com.tooomor.online.store.delivery.model;

import java.util.List;

public class Order {
    String orderNumber;
    Client client;
    List<OrderItem> orderItems;

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Order(String orderNumber, Client client, List<OrderItem> orderItems) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.orderItems = orderItems;
    }
}
