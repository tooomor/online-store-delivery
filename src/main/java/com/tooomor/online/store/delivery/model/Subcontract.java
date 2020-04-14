package com.tooomor.online.store.delivery.model;

public class Subcontract {
    String orderNumber;
    Warehouse warehouse;
    Integer waypoint;

    public Integer getWaypoint() {
        return waypoint;
    }

    public void setWaypoint(Integer waypoint) {
        this.waypoint = waypoint;
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

    public Subcontract(String orderNumber, Warehouse warehouse, Integer waypoint) {
        this.orderNumber = orderNumber;
        this.warehouse = warehouse;
        this.waypoint = waypoint;
    }
}
