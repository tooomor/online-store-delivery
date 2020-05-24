package com.tooomor.online.store.delivery.service;

import com.tooomor.online.store.delivery.model.OrderDTO;

public interface DeliveryService {

    OrderDTO calculateWay(OrderDTO orderDTO);
}
