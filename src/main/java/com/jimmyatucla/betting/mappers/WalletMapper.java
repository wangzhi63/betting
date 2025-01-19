package com.jimmyatucla.betting.mappers;
import org.springframework.stereotype.Component;

import com.jimmyatucla.betting.dtos.WalletDTO;
import com.jimmyatucla.betting.entities.Wallet;

@Component
public class WalletMapper {

    public WalletDTO toWalletDTO(Wallet wallet) {
        if (wallet == null) {
            return null;
        }

        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(wallet.getId());
        walletDTO.setUserId(wallet.getUserId());
        walletDTO.setBalance(wallet.getBalance());

        return walletDTO;
    }
}




