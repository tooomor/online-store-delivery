package com.tooomor.online.store.delivery.utils;

import com.tooomor.online.store.delivery.model.GeolocationDTO;
import com.tooomor.online.store.delivery.model.Waypoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NavigationHelper {

    private Double distance;
    private List<Waypoint> optimalRoute;
    @Builder.Default
    private List<List<Waypoint>> routes = new ArrayList<>();

    public void findAllRoutes(Integer numOfWaypoints, List<Waypoint> waypoints) {
        if (numOfWaypoints == 1) {
            this.routes.add(new ArrayList<>(waypoints));
        } else {
            for (int i = 0; i < numOfWaypoints - 1; i++) {
                findAllRoutes(numOfWaypoints - 1, waypoints);
                if (numOfWaypoints % 2 == 0) {
                    Collections.swap(waypoints, i, numOfWaypoints - 1);
                } else {
                    Collections.swap(waypoints, 0, numOfWaypoints - 1);
                }
            }
            findAllRoutes(numOfWaypoints - 1, waypoints);
        }
    }

    public void findOptimalRoute(
            List<List<Waypoint>> routes,
            GeolocationDTO startingPoint,
            GeolocationDTO destination) {
        this.distance = Double.MAX_VALUE;
        routes.stream().forEach(route -> {
            AtomicReference<Double> distance = new AtomicReference<>(0.0);
            AtomicReference<Point2D> point = new AtomicReference<>(locationToPoint(startingPoint));
            route.stream().forEach(wp -> {
                Point2D nextPoint = locationToPoint(wp.getLocation());
                distance.getAndSet(distance.get() + point.get().distance(nextPoint));
                point.set(nextPoint);
            });
            distance.getAndSet( distance.get() + point.get().distance(locationToPoint(destination)));
            if (this.distance > distance.get()) {
                this.distance = distance.get();
                this.optimalRoute = route;
            }
        });
    }

    public void findOptimalRoute(GeolocationDTO startingPoint, GeolocationDTO destination, List<Waypoint> waypoints) {
        findAllRoutes(waypoints.size(), waypoints);
        findOptimalRoute(this.routes, startingPoint, destination);
    }

    public Point2D.Double locationToPoint(GeolocationDTO location) {
        return new Point2D.Double(location.getPointX(), location.getPointY());
    }
}
