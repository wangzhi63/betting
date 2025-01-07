package com.jimmyatucla.betting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimmyatucla.betting.dtos.OrderDTO;
import com.jimmyatucla.betting.entities.Order;
import com.jimmyatucla.betting.entities.Order.OrderStatus;
import com.jimmyatucla.betting.mappers.OrderMapper;
import com.jimmyatucla.betting.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                     .map(orderMapper::toDto)
                     .toList();
    }

    public Optional<OrderDTO> findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(orderMapper::toDto);
    }

    public OrderDTO save(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDTO update(Long id, OrderDTO orderDTO) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        Order order = orderMapper.toEntity(orderDTO);
        order.setId(id);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    public List<OrderDTO> findBySellerId(Long sellerId) {
        List<Order> orders = orderRepository.findBySellerId(sellerId);
        return orders.stream()
                     .map(orderMapper::toDto)
                     .toList();
    }

    public List<OrderDTO> findByBuyerId(Long buyerId) {
        List<Order> orders = orderRepository.findByBuyerId(buyerId);
        return orders.stream()
                     .map(orderMapper::toDto)
                     .toList();
    }

    public List<OrderDTO> findBySellerIdAndStatus(Long sellerId, String status) {
        List<Order> orders = orderRepository.findBySellerIdAndStatus(sellerId, OrderStatus.valueOf(status));
        return orders.stream()
                     .map(orderMapper::toDto)
                     .toList();
    }

    public List<OrderDTO> findByBuyerIdAndStatus(Long buyerId, String status) {
        List<Order> orders = orderRepository.findByBuyerIdAndStatus(buyerId, OrderStatus.valueOf(status));
        return orders.stream()
                     .map(orderMapper::toDto)
                     .toList();
    }


}
