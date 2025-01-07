package com.jimmyatucla.betting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jimmyatucla.betting.dtos.OrderDTO;
import com.jimmyatucla.betting.entities.Order;
import com.jimmyatucla.betting.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.save(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.update(id, orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersBySeller(@PathVariable Long sellerId) {
        List<OrderDTO> orders = orderService.findBySellerId(sellerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByBuyer(@PathVariable Long buyerId) {
        List<OrderDTO> orders = orderService.findByBuyerId(buyerId);
        return ResponseEntity.ok(orders);
    }
}