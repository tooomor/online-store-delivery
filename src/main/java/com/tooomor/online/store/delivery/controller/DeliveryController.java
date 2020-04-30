package com.tooomor.online.store.delivery.controller;

import com.tooomor.online.store.delivery.model.OrderDTO;
import com.tooomor.online.store.delivery.model.OrderItemDTO;
import com.tooomor.online.store.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/store/v1/delivery")
@RestController
public class DeliveryController {

    DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<List<OrderItemDTO>> calculateWay(@RequestBody OrderDTO orderDTO){

        List<OrderItemDTO> orderItems = deliveryService.calculateWay(orderDTO);

        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }
}
