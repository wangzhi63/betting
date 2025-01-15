package com.jimmyatucla.betting.mappers;

import com.jimmyatucla.betting.dtos.ResolutionDTO;
import com.jimmyatucla.betting.entities.Resolution;

public class ResolutionMapper {

    public static ResolutionDTO toResolutionDTO(Resolution resolution) {
        ResolutionDTO dto = new ResolutionDTO();
        dto.setId(resolution.getId());
        dto.setContractId(resolution.getContractId());
        dto.setStatus(resolution.getStatus());
        dto.setDecision(resolution.getDecision());
        return dto;
    }

    public static Resolution toResolution(ResolutionDTO resolutionDTO) {
        Resolution resolution = new Resolution();
        resolution.setId(resolutionDTO.getId());
        resolution.setContractId(resolutionDTO.getContractId());
        resolution.setStatus(resolutionDTO.getStatus());
        resolution.setDecision(resolutionDTO.getDecision());
        return resolution;
    }
}