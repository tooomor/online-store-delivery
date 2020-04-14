package com.tooomor.online.store.delivery.utils;

import com.tooomor.online.store.delivery.model.Location;
import com.tooomor.online.store.delivery.model.Warehouse;
import lombok.extern.slf4j.Slf4j;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class NavigationHelper {

    private Double distance;
    private List<String> optimalRoute;
    private List<List<String>> routes;
    private HashMap<String, Location> navMap;

    public NavigationHelper(List<Warehouse> warehouses){
        this.distance = 0.0;
        this.optimalRoute = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.navMap = setMap(warehouses);
    }

    private HashMap<String, Location> setMap(List<Warehouse> warehouses) {
        HashMap<String, Location> navMap = new HashMap<>();
        warehouses.stream().forEach(wh -> navMap.put(wh.getCode(), wh.getAddress().getLocation()));
        return navMap;
    }

    public NavigationHelper(Double distance, List<String> optimalRoute) {
        this.distance = distance;
        this.optimalRoute = optimalRoute;
    }

    public List<List<String>> getRoutes() {
        return routes;
    }

    public Map<String, Location> getNavMap() {
        return navMap;
    }

    public Double getDistance() {
        return distance;
    }

    private void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<String> getOptimalRoute() {
        return optimalRoute;
    }

    private void setOptimalRoute(List<String> optimalRoute) {
        this.optimalRoute = optimalRoute;
    }

    public void findAllRoutes(Integer waypoints, List<Warehouse> warehouses) {
        if(waypoints == 1) {
            this.routes.add(warehouses.stream().map(Warehouse::getCode).collect(Collectors.toList()));
        } else {
            for(int i = 0; i < waypoints-1; i++) {
                findAllRoutes(waypoints - 1, warehouses);
                if(waypoints % 2 == 0) {
                    Collections.swap(warehouses, i, waypoints-1);

                } else {
                    Collections.swap(warehouses, 0, waypoints-1);
                }
            }
            findAllRoutes(waypoints - 1, warehouses);
        }
    }

    public void findOptimalRoute(
            List<List<String>> routes,
            Location startingPoint,
            Location destination) {
        this.distance = Double.MAX_VALUE;
        NavigationHelper navigationHelper = new NavigationHelper(Double.MAX_VALUE, null);
        routes.stream().forEach(route -> {
            Double distance = 0.0;
            Point2D waypoint = locationToPoint(startingPoint);
            for(String location : route){
                Point2D newWaypoint = locationToPoint(this.navMap.get(location));
                distance += waypoint.distance(newWaypoint);
                waypoint = newWaypoint;
            }
            if (this.distance > distance) {
                this.distance = distance;
                this.optimalRoute = route;
            }
        });
    }

    public void findOptimalRoute(Location startingPoint, Location destination){
        findOptimalRoute(this.routes, startingPoint, destination);
    }

    public Point2D.Double locationToPoint(Location location) {
        return new Point2D.Double(location.getPointX(), location.getPointY());
    }

    @Override
    public String toString() {
        return "NavigationHelper{" +
                "distance=" + distance +
                ", optimalRoute=" + optimalRoute +
                ", routes=" + routes +
                '}';
    }
}
