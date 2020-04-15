package com.tooomor.online.store.delivery.model;

public class OrderItem {
    String orderNumber;
    Warehouse warehouse;
    Integer waypointNo;

    public Integer getWaypointNo() {
        return waypointNo;
    }

    public void setWaypointNo(Integer waypointNo) {
        this.waypointNo = waypointNo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public OrderItem(String orderNumber, Warehouse warehouse, Integer waypointNo) {
        this.orderNumber = orderNumber;
        this.warehouse = warehouse;
        this.waypointNo = waypointNo;
    }
}
