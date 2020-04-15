package com.tooomor.online.store.delivery.model;

public class Waypoint {
    String id;
    Address address;
    Location location;

    public Waypoint(String id, Address address, Location location) {
        this.id = id;
        this.address = address;
        this.location = location;
    }

    public Waypoint(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "id='" + id + '\'' +
                ", address=" + address +
                ", location=" + location +
                '}';
    }
}
