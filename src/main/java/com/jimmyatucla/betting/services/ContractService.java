// package com.jimmyatucla.betting.services;Service

// import com.jimmyatucla.betting.entities.*;
// import com.jimmyatucla.betting.repositories.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class ContractService {
//     @Autowired
//     private ContractRepository contractRepository;

//     public List<Contract> findAll() {
//         return contractRepository.findAll();
//     }

//     public Optional<Contract> findById(Long id) {
//         return contractRepository.findById(id);
//     }

//     public Contract save(Contract contract) {
//         return contractRepository.save(contract);
//     }

//     public void deleteById(Long id) {
//         contractRepository.deleteById(id);
//     }
// }

package com.jimmyatucla.betting.services;

import com.jimmyatucla.betting.dtos.ContractDTO;
import com.jimmyatucla.betting.entities.Contract;
import com.jimmyatucla.betting.mappers.ContractMapper;
import com.jimmyatucla.betting.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class ContractService {

    private static final Logger logger = LogManager.getLogger(ContractService.class);

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractMapper contractMapper;

    public List<ContractDTO> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream()
                .map(contractMapper::contractToContractDTO)
                .collect(Collectors.toList());
    }

    public ContractDTO getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        return contractMapper.contractToContractDTO(contract);
    }

    public ContractDTO createContract(ContractDTO contractDTO) {
        System.out.println("Converting ContractDTO to Contract: " + contractDTO);
        logger.debug("Converting ContractDTO to Contract: {}", contractDTO);

        Contract contract = contractMapper.contractDTOToContract(contractDTO);
        System.out.println("Converted Contract: " + contract);
        logger.debug("Converted Contract: {}", contract);
        Contract savedContract = contractRepository.save(contract);
        return contractMapper.contractToContractDTO(savedContract);
    }

    public ContractDTO updateContract(Long id, ContractDTO contractDTO) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        
        existingContract.setTitle(contractDTO.getTitle());
        existingContract.setDescription(contractDTO.getDescription());
        existingContract.setAssertionText(contractDTO.getAssertionText());
        existingContract.setStartDate(contractDTO.getStartDate());
        existingContract.setEndDate(contractDTO.getEndDate());

        Contract updatedContract = contractRepository.save(existingContract);
        return contractMapper.contractToContractDTO(updatedContract);
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }
}