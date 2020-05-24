package com.tooomor.online.store.delivery.controller;

import com.tooomor.online.store.delivery.model.OrderDTO;
import com.tooomor.online.store.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/store/v1/delivery")
@RestController
public class DeliveryController {

    DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<OrderDTO> calculateWay(@RequestBody OrderDTO orderDTO){
        OrderDTO orderItemsAndLength = deliveryService.calculateWay(orderDTO);
        return new ResponseEntity<>(orderItemsAndLength, HttpStatus.OK);
    }
}
