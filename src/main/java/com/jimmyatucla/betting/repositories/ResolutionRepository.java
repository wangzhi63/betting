package com.jimmyatucla.betting.repositories;

import com.jimmyatucla.betting.entities.Resolution;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
    
    // Resolution findByStatus(String status);
    List<Resolution> findByStatus(String status);

}