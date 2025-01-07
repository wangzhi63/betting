package com.jimmyatucla.betting.mappers;

import org.springframework.stereotype.Component;

import com.jimmyatucla.betting.dtos.OrderDTO;

import com.jimmyatucla.betting.entities.Order;
import com.jimmyatucla.betting.entities.Order.OrderStatus;


@Component
public class OrderMapper {

    public OrderDTO toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setContractId(order.getContract().getId());
        orderDTO.setSellerId(order.getSeller().getId());
        orderDTO.setBuyerId(order.getBuyer().getId());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setStatus(order.getStatus().name());
        orderDTO.setCreatedById(order.getCreatedBy().getId());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setUpdatedAt(order.getUpdatedAt());

        return orderDTO;
    }

    public Order toEntity(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setContractId(orderDTO.getContractId());
        order.setSellerId(orderDTO.getSellerId());
        order.setBuyerId(orderDTO.getBuyerId());
        order.setPrice(orderDTO.getPrice());
        order.setQuantity(orderDTO.getQuantity());
        order.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));
        order.setCreatedById(orderDTO.getCreatedById());
        order.setCreatedAt(orderDTO.getCreatedAt());
        order.setUpdatedAt(orderDTO.getUpdatedAt());

        return order;
    }

}
