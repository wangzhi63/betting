package com.jimmyatucla.betting.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimmyatucla.betting.entities.Transaction;
import com.jimmyatucla.betting.entities.Wallet;
import com.jimmyatucla.betting.repositories.TransactionRepository;
import com.jimmyatucla.betting.repositories.WalletRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void addAmount(Long userId, Double amount, String description) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet == null) {
            throw new RuntimeException("Wallet not found");
        }

        // Update wallet balance
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);

        // Record the transaction
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(amount); // Credit
        transaction.setType("credit");
        transaction.setDescription(description);
        transactionRepository.save(transaction);
    }

    @Transactional
    public void removeAmount(Long userId, Double amount, String description) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet == null) {
            throw new RuntimeException("Wallet not found");
        }

        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Update wallet balance
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);

        // Record the transaction
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(-amount); // Debit
        transaction.setType("debit");
        transaction.setDescription(description);
        transactionRepository.save(transaction);
    }
}