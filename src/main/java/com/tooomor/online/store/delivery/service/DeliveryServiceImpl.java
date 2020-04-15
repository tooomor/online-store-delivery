package com.tooomor.online.store.delivery.service;

import com.tooomor.online.store.delivery.model.*;
import com.tooomor.online.store.delivery.utils.NavigationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@ConfigurationProperties(prefix = "sfg.delivery", ignoreInvalidFields = false)
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private Location main_wh_location = new Location();

    public void setMain_wh_location(Location main_wh_location) {
        this.main_wh_location = main_wh_location;
    }

    @Override
    public List<OrderItem> calculateWay(Order order) {
        log.info(main_wh_location.toString());
        List<Waypoint> waypoints = order.getOrderItems()
                .stream()
                .map(oi -> {
                    Warehouse wh = oi.getWarehouse();
                    return new Waypoint(wh.getCode(), wh.getAddress().getLocation());
                })
                .collect(Collectors.toList());
        NavigationHelper navigationHelper = new NavigationHelper();
        navigationHelper.findOptimalRoute(main_wh_location, order.getClient().getAddress().getLocation(), waypoints);
        AtomicReference<Integer> priority = new AtomicReference<>(1);
        navigationHelper.getOptimalRoute().stream().forEach(wp -> {
            order.getOrderItems()
                    .stream()
                    .filter(sub -> sub.getWarehouse().getCode().equals(wp.getId()))
                    .forEach(sub -> sub.setWaypointNo(priority.get()));
            priority.getAndSet(priority.get() + 1);
            });
        log.info(navigationHelper.toString());
        return order.getOrderItems();
    }

}
