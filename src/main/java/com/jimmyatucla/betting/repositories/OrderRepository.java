package com.jimmyatucla.betting.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jimmyatucla.betting.entities.Order;
import com.jimmyatucla.betting.entities.Order.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findBySellerId(Long sellerId);
    List<Order> findByBuyerId(Long buyerId);
    List<Order> findBySellerIdAndStatus(Long sellerId, OrderStatus status);
    List<Order> findByBuyerIdAndStatus(Long buyerId, OrderStatus status);
    List<Order> findByContractIdAndStatus(Long contractId, OrderStatus status);
}
