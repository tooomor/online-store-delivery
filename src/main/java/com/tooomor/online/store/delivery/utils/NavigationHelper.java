package com.tooomor.online.store.delivery.utils;

import com.tooomor.online.store.delivery.model.Location;
import com.tooomor.online.store.delivery.model.Waypoint;
import lombok.extern.slf4j.Slf4j;

import java.awt.geom.Point2D;
import java.util.*;

@Slf4j
public class NavigationHelper {

    private Double distance;
    private List<Waypoint> optimalRoute;
    private List<List<Waypoint>> routes;

    public NavigationHelper(){
        this.distance = 0.0;
        this.optimalRoute = new ArrayList<>();
        this.routes = new ArrayList<>();
    }

    public void findAllRoutes(List<Waypoint> waypoints){
        findAllRoutes(waypoints.size(), waypoints);
    }

    public void findAllRoutes(Integer numOfWaypoints, List<Waypoint> waypoints) {
        if(numOfWaypoints == 1) {
            this.routes.add(new ArrayList<>(waypoints));
        } else {
            for(int i = 0; i < numOfWaypoints-1; i++) {
                findAllRoutes(numOfWaypoints - 1, waypoints);
                if(numOfWaypoints % 2 == 0) {
                    Collections.swap(waypoints, i, numOfWaypoints-1);
                } else {
                    Collections.swap(waypoints, 0, numOfWaypoints-1);
                }
            }
            findAllRoutes(numOfWaypoints - 1, waypoints);
        }
    }

    public void findOptimalRoute(
            List<List<Waypoint>> routes,
            Location startingPoint,
            Location destination) {
        this.distance = Double.MAX_VALUE;
        routes.stream().forEach(route -> {
            Double distance = 0.0;
            Point2D point = locationToPoint(startingPoint);
            for(Waypoint waypoint : route){
                Point2D nextPoint = locationToPoint(waypoint.getLocation());
                distance += point.distance(nextPoint);
                point = nextPoint;
            }
            distance+=point.distance(locationToPoint(destination));
            if (this.distance > distance) {
                this.distance = distance;
                this.optimalRoute = route;
            }
        });
    }

    public void findOptimalRoute(Location startingPoint, Location destination, List<Waypoint> waypoints){
        findAllRoutes(waypoints.size(), waypoints);
        findOptimalRoute(this.routes, startingPoint, destination);
    }

    public Point2D.Double locationToPoint(Location location) {
        return new Point2D.Double(location.getPointX(), location.getPointY());
    }
    public List<List<Waypoint>> getRoutes() {
        return routes;
    }

    public Double getDistance() {
        return distance;
    }

    private void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<Waypoint> getOptimalRoute() {
        return optimalRoute;
    }

    private void setOptimalRoute(List<Waypoint> optimalRoute) {
        this.optimalRoute = optimalRoute;
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
