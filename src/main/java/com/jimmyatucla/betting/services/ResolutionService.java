package com.jimmyatucla.betting.services;

import com.jimmyatucla.betting.entities.*;
import com.jimmyatucla.betting.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResolutionService {
    @Autowired
    private ResolutionRepository resolutionRepository;

    public List<Resolution> findAll() {
        return resolutionRepository.findAll();
    }

    public Optional<Resolution> findById(Long id) {
        return resolutionRepository.findById(id);
    }

    public Resolution save(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }

    public void deleteById(Long id) {
        resolutionRepository.deleteById(id);
    }
}
