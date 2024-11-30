package com.jimmyatucla.betting.repositories;

import com.jimmyatucla.betting.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {}