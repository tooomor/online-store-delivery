package com.tooomor.online.store.delivery.service;

import com.tooomor.online.store.delivery.model.Order;
import com.tooomor.online.store.delivery.model.OrderItem;

import java.util.List;

public interface DeliveryService {

    List<OrderItem> calculateWay(Order order);
}
