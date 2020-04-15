package com.tooomor.online.store.delivery.model;

import java.util.UUID;

public class Location {
    Double pointX;
    Double pointY;

    public Location() {
    }

    public Location(Double pointX, Double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public Double getPointX() {
        return pointX;
    }

    public void setPointX(Double pointX) {
        this.pointX = pointX;
    }

    public Double getPointY() {
        return pointY;
    }

    public void setPointY(Double pointY) {
        this.pointY = pointY;
    }

    @Override
    public String toString() {
        return "Location{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                '}';
    }
}
