package com.jimmyatucla.betting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jimmyatucla.betting.dtos.ResolutionDTO;
import com.jimmyatucla.betting.services.ResolutionService;

import java.util.List;

@RestController
@RequestMapping("/api/resolutions")
public class ResolutionController {

    @Autowired
    private ResolutionService resolutionService;

    @GetMapping("/pending")
    public ResponseEntity<List<ResolutionDTO>> listPendingResolutions() {
        List<ResolutionDTO> pendingResolutions = resolutionService.getPendingResolutions();
        return ResponseEntity.ok(pendingResolutions);
    }

    @PutMapping("/{id}/decision")
    public ResponseEntity<ResolutionDTO> updateDecision(@PathVariable Long id, @RequestBody String decisionDTO) {
        ResolutionDTO updatedResolution = resolutionService.updateDecision(id, decisionDTO);
        return ResponseEntity.ok(updatedResolution);
    }
}
