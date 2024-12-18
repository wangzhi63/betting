package com.jimmyatucla.betting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jimmyatucla.betting.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUserId(Long userId);
}