package com.jimmyatucla.betting.mappers;

import com.jimmyatucla.betting.dtos.BidDTO;
import com.jimmyatucla.betting.entities.Bid;

public class BidMapper {
    public static BidDTO toDTO(Bid bid) {
        BidDTO dto = new BidDTO();
        dto.setId(bid.getId());
        dto.setAmount(bid.getAmount());
        dto.setAction(bid.getAction());
        dto.setCreatedAt(bid.getCreatedAt());
        dto.setUserId(bid.getUserId());
        dto.setContractId(bid.getContractId());
        return dto;
    }

    public static Bid toEntity(BidDTO dto) {
        Bid bid = new Bid();
        bid.setId(dto.getId());
        bid.setAmount(dto.getAmount());
        bid.setAction(dto.getAction());
        bid.setCreatedAt(dto.getCreatedAt());
        bid.setUserId(dto.getUserId());
        bid.setContractId(dto.getContractId());
        return bid;
    }
}
