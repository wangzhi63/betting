package com.jimmyatucla.betting.services;

import com.jimmyatucla.betting.dtos.ResolutionDTO;
import com.jimmyatucla.betting.entities.*;
import com.jimmyatucla.betting.exceptions.ResolutionNotFoundException;
import com.jimmyatucla.betting.mappers.ResolutionMapper;
import com.jimmyatucla.betting.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResolutionService {
    @Autowired
    private ResolutionRepository resolutionRepository;

     public List<ResolutionDTO> getPendingResolutions() {
        List<Resolution> pendingResolutions = resolutionRepository.findByStatus("PENDING");
        return pendingResolutions.stream()
                .map(ResolutionMapper::toResolutionDTO)
                .collect(Collectors.toList());
    }

    public ResolutionDTO updateDecision(Long id, String decision) {
        Resolution resolution = resolutionRepository.findById(id)
                .orElseThrow(() -> new ResolutionNotFoundException("Resolution not found with id " + id));
        resolution.setDecision(decision);
        resolution.setStatus("DECIDED");
        resolutionRepository.save(resolution);
        return ResolutionMapper.toResolutionDTO(resolution);
    }

    public List<Resolution> findAll() {
        return resolutionRepository.findAll();
    }

    public Optional<Resolution> findById(Long id) {
        return resolutionRepository.findById(id);
    }

    public Resolution save(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }

    public Resolution update(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }  

    public void deleteById(Long id) {
        resolutionRepository.deleteById(id);
    }
}
