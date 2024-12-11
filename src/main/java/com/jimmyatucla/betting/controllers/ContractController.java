package com.jimmyatucla.betting.controllers;



import com.jimmyatucla.betting.dtos.ContractDTO;
import com.jimmyatucla.betting.entities.Contract;
import com.jimmyatucla.betting.services.ContractService;
import com.jimmyatucla.betting.mappers.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping
    public List<ContractDTO> getAllContracts() {
        List<ContractDTO> contracts = contractService.getAllContracts();
        return contracts;
    }

    @PostMapping
    public ResponseEntity<ContractDTO> createContract(@RequestBody ContractDTO contractDTO) {
        ContractDTO savedContractDTO = contractService.createContract(contractDTO);
        return ResponseEntity.ok(savedContractDTO);
    }
} 
