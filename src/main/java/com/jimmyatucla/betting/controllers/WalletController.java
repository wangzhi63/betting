package com.jimmyatucla.betting.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jimmyatucla.betting.dtos.WalletDTO;
import com.jimmyatucla.betting.services.WalletService;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/{userId}")
    public WalletDTO getWalletByUserId(@PathVariable Long userId) {
        return walletService.findByUserId(userId);
    }
}