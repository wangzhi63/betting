package com.jimmyatucla.betting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jimmyatucla.betting.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
} 
