package com.tooomor.online.store.delivery.service;

import com.tooomor.online.store.delivery.model.OrderDTO;
import com.tooomor.online.store.delivery.model.OrderItemDTO;

import java.util.List;

public interface DeliveryService {

    List<OrderItemDTO> calculateWay(OrderDTO orderDTO);
}
