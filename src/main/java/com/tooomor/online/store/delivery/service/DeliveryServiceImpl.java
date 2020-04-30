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

    private Geolocation main_wh_location = new Geolocation();

    public void setMain_wh_location(Geolocation main_wh_location) {
        this.main_wh_location = main_wh_location;
    }

    @Override
    public List<OrderItemDTO> calculateWay(OrderDTO orderDTO) {
        log.info(main_wh_location.toString());
        List<Waypoint> waypoints = orderDTO.getOrderItems()
                .stream()
                .map(oi -> {
                    WarehouseDTO wh = oi.getWarehouse();
                    //TODO
                    return Waypoint.builder().id(wh.getCode()).location(wh.getAddress().getLocation()).build();
                })
                .collect(Collectors.toList());
        NavigationHelper navigationHelper = new NavigationHelper();
        //TODO
        navigationHelper.findOptimalRoute(main_wh_location, orderDTO.getClient().getAddress().getLocation(), waypoints);
        AtomicReference<Integer> priority = new AtomicReference<>(1);
        navigationHelper.getOptimalRoute().stream().forEach(wp -> {
            orderDTO.getOrderItems()
                    .stream()
                    .filter(sub -> sub.getWarehouse().getCode().equals(wp.getId()))
                    .forEach(sub -> sub.setWaypointNo(priority.get()));
            priority.getAndSet(priority.get() + 1);
            });
        log.info(navigationHelper.toString());
        return orderDTO.getOrderItems();
    }

}
