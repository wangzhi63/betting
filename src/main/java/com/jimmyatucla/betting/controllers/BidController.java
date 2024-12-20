package com.jimmyatucla.betting.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jimmyatucla.betting.dtos.BidDTO;
import com.jimmyatucla.betting.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @PostMapping
    public ResponseEntity<BidDTO> createBid(@RequestBody BidDTO bidDTO) {
        // BidDTO createdBid = bidService.createBid(bidDTO);
        BidDTO createdBid = bidService.placeBid(bidDTO);
        return new ResponseEntity<>(createdBid, HttpStatus.CREATED);
    }
}