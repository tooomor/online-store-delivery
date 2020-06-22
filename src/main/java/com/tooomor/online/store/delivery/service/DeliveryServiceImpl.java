package com.tooomor.online.store.delivery.service;

import com.tooomor.online.store.delivery.model.*;
import com.tooomor.online.store.delivery.utils.NavigationHelper;
import com.tooomor.online.store.delivery.warehouse.WarehouseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@ConfigurationProperties(prefix = "sfg.delivery", ignoreInvalidFields = false)
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    WarehouseClient warehouseClient;

    private GeolocationDTO main_wh_location = new GeolocationDTO();

    public void setMain_wh_location(GeolocationDTO main_wh_location) {
        this.main_wh_location = main_wh_location;
    }

    @Override
    public OrderDTO calculateWay(OrderDTO orderDTO) {
        var orderItems = calculateOptimalWay(orderDTO);
        return OrderDTO
                .builder()
                .orderId(orderDTO.getOrderId())
                .geolocation(orderDTO.getGeolocation())
                .orderItems(orderItems)
                .routeLength(calculateRouteLength(orderItems))
                .build();
    }

    public List<OrderItemDTO> calculateOptimalWay(OrderDTO orderDTO) {
        log.info(main_wh_location.toString());
        List<Waypoint> waypoints = orderDTO.getOrderItems()
                .stream()
                .map(oi -> {
                    WarehouseDTO wh = oi.getWarehouse();
                    var whGeoLocation = warehouseClient.getGeolocationByWarehouseId(wh.getCode());
                    return Waypoint
                            .builder()
                            .id(wh.getCode())
                            .location(whGeoLocation)
                            .build();
                })
                .collect(Collectors.toList());
        NavigationHelper navigationHelper = new NavigationHelper();
        //TODO
        navigationHelper.findOptimalRoute(main_wh_location, orderDTO.getGeolocation(), waypoints);
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

    public Integer calculateRouteLength(List<OrderItemDTO> orderItemDTOList) {
        return orderItemDTOList
                .stream()
                .map(item->item.getWarehouse().getCode())
                .distinct()
                .collect(Collectors.toList())
                .size();
    }

}