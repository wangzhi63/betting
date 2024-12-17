

package com.jimmyatucla.betting.services;

import com.jimmyatucla.betting.dtos.ContractDTO;
import com.jimmyatucla.betting.dtos.ContractWithBidsDTO;
import com.jimmyatucla.betting.entities.Contract;
import com.jimmyatucla.betting.mappers.ContractMapper;
import com.jimmyatucla.betting.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;

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

  

    public List<ContractWithBidsDTO> getAllContractsWithBids() {
        List<Map<String, Object>> results = contractRepository.findAllContractsWithBids();
        List<ContractWithBidsDTO> dtos = new ArrayList<>();

        for (Map<String, Object> result : results) {
            ContractWithBidsDTO dto = new ContractWithBidsDTO();
            dto.setId(((Number) result.get("id")).longValue());
            dto.setAssertionText((String) result.get("assertion_text"));

            Date sqlDate = (Date) result.get("end_date");
            LocalDate localDate = sqlDate.toLocalDate();
            dto.setEndDate(localDate);

            dto.setLongAmount((Double) result.get("long_amount"));
            dto.setShortAmount((Double) result.get("short_amount"));
            dto.setLongCount((Long) result.get("long_count"));
            dto.setShortCount((Long) result.get("short_count"));
            dtos.add(dto);
        }

        return dtos;
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