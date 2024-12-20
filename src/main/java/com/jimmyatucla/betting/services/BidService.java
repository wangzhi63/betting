package com.jimmyatucla.betting.services;

import com.jimmyatucla.betting.dtos.BidDTO;
import com.jimmyatucla.betting.entities.*;
import com.jimmyatucla.betting.mappers.BidMapper;
import com.jimmyatucla.betting.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    public Optional<Bid> findById(Long id) {
        return bidRepository.findById(id);
    }

    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    public void deleteById(Long id) {
        bidRepository.deleteById(id);
    }

    public BidDTO createBid(BidDTO bidDTO) {
        Bid bid = BidMapper.toEntity(bidDTO);
        Bid savedBid = bidRepository.save(bid);
        return BidMapper.toDTO(savedBid);
    }

    @Transactional
    public BidDTO placeBid(BidDTO bidDTO) {
        Long userId = bidDTO.getUserId();
        Long contractId = bidDTO.getContractId();
        Double amount = bidDTO.getAmount();
        String action = bidDTO.getAction();

        // Create a transaction for the bid
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(-amount); // Debit
        transaction.setType("debit");
        transaction.setDescription("Bid on contract " + contractId + " with action " + action);
        transaction = transactionRepository.save(transaction);

        // Update the user's wallet
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet == null) {
            throw new RuntimeException("Wallet not found");
        }
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);

        // Record the bid with the transaction ID
        Bid bid = new Bid();
        bid.setUserId(userId);
        bid.setContractId(contractId);
        bid.setAmount(amount);
        bid.setTransaction(transaction);
        bid.setCreatedAt(transaction.getCreatedAt());
        bid.setAction(action);
        Bid savedBid = bidRepository.save(bid);

        return BidMapper.toDTO(savedBid);
    }
}
