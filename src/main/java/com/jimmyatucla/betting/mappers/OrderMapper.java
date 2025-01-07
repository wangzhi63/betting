package com.jimmyatucla.betting.mappers;

import org.springframework.stereotype.Component;

import com.jimmyatucla.betting.dtos.OrderDTO;
import com.jimmyatucla.betting.entities.Contract;
import com.jimmyatucla.betting.entities.Order;
import com.jimmyatucla.betting.entities.Order.OrderStatus;
import com.jimmyatucla.betting.entities.User;

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
        orderDTO.setCreatedBy(order.getCreatedBy().getId());
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
        // Assuming you have methods to fetch Contract, User (Seller and Buyer) by their IDs
        order.setContract(fetchContractById(orderDTO.getContractId()));
        order.setSeller(fetchUserById(orderDTO.getSellerId()));
        order.setBuyer(fetchUserById(orderDTO.getBuyerId()));
        order.setPrice(orderDTO.getPrice());
        order.setQuantity(orderDTO.getQuantity());
        order.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));
        order.setCreatedBy(fetchUserById(orderDTO.getCreatedBy()));
        order.setCreatedAt(orderDTO.getCreatedAt());
        order.setUpdatedAt(orderDTO.getUpdatedAt());

        return order;
    }

    // Placeholder methods for fetching entities by ID
    private Contract fetchContractById(Long id) {
        // Implement this method to fetch Contract by ID
        return new Contract(); // Replace with actual fetching logic
    }

    private User fetchUserById(Long id) {
        // Implement this method to fetch User by ID
        return new User(); // Replace with actual fetching logic
    }
}
