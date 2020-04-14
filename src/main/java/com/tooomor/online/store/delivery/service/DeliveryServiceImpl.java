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
    public List<Subcontract> calculateWay(Order order) {
        log.info(main_wh_location.toString());
        List<Warehouse> warehouses = order.getSubcontracts().stream().map(Subcontract::getWarehouse).collect(Collectors.toList());
        NavigationHelper navigationHelper = new NavigationHelper(warehouses);
        navigationHelper.findAllRoutes(warehouses.size(), warehouses);
        navigationHelper.findOptimalRoute(main_wh_location, order.getClient().getAddress().getLocation());
        AtomicReference<Integer> priority = new AtomicReference<>(1);
        navigationHelper.getOptimalRoute().stream().forEach(wh -> {
            order.getSubcontracts()
                    .stream()
                    .filter(sub -> sub.getWarehouse().getCode() == wh)
                    .forEach(sub -> sub.setWaypoint(priority.get()));
            priority.getAndSet(priority.get() + 1);
            });
        log.info(navigationHelper.toString());
        return order.getSubcontracts();
    }

}
