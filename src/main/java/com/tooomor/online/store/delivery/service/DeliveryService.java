package com.tooomor.online.store.delivery.service;

import com.tooomor.online.store.delivery.model.Order;
import com.tooomor.online.store.delivery.model.Subcontract;

import java.util.List;

public interface DeliveryService {

    List<Subcontract> calculateWay(Order order);
}
