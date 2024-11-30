package com.jimmyatucla.betting.repositories;

import com.jimmyatucla.betting.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {}