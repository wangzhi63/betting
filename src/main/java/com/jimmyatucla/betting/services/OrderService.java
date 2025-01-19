package com.jimmyatucla.betting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimmyatucla.betting.dtos.BidDTO;
import com.jimmyatucla.betting.dtos.OrderDTO;
import com.jimmyatucla.betting.dtos.ResolutionDTO;
import com.jimmyatucla.betting.entities.Bid;
import com.jimmyatucla.betting.entities.Order;
import com.jimmyatucla.betting.entities.Order.OrderStatus;
import com.jimmyatucla.betting.mappers.OrderMapper;
import com.jimmyatucla.betting.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

import com.jimmyatucla.betting.exceptions.OrderNotFoundException;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BidService bidService;

    @Autowired
    private WalletService walletService;

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

    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + orderId));
        order.setStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
        if(status.equals("ACCEPTED")) {
            BidDTO sellerBitDTO = new BidDTO();
            sellerBitDTO.setUserId(order.getSellerId());
            sellerBitDTO.setAmount((100.00 - order.getPrice().doubleValue()) * order.getQuantity());
            sellerBitDTO.setContractId(order.getContractId());
            sellerBitDTO.setAction("short");
            bidService.placeBid(sellerBitDTO);

            BidDTO buyerBitDTO = new BidDTO();
            buyerBitDTO.setUserId(order.getBuyerId());
            buyerBitDTO.setAmount((order.getPrice().doubleValue()) * order.getQuantity());
            buyerBitDTO.setContractId(order.getContractId());
            buyerBitDTO.setAction("long");
            bidService.placeBid(buyerBitDTO);

        }
    }

    public void settleOrdersForResolution(ResolutionDTO resolutionDTO) {
        Long contractId = resolutionDTO.getContractId();
        List<Order> orders = orderRepository.findByContractIdAndStatus(contractId, OrderStatus.ACCEPTED);
        for (Order order : orders) {
           settleOrder(order.getId(), resolutionDTO);
        }
    }
    
    @Transactional
    public void settleOrder(Long orderId, ResolutionDTO resolutionDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + orderId));
        order.setStatus(OrderStatus.TRANSACTED);
        orderRepository.save(order);

        String decision = resolutionDTO.getDecision();
        Long winnerId;
        if(decision.equals("true")){
          winnerId = order.getBuyerId();
        } else {
            winnerId = order.getSellerId();
        }
        Double amount = (double) (100L*order.getQuantity());

        walletService.addAmount(winnerId, amount, "winning order " + orderId);


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
