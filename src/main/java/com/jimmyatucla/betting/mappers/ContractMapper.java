package com.jimmyatucla.betting.mappers;



import com.jimmyatucla.betting.dtos.ContractDTO;
import com.jimmyatucla.betting.entities.Contract;
import org.mapstruct.Mapper;
// import org.mapstruct.factory.Mappers;
// import org.mapstruct.Mapping;

// @Mapper(componentModel = "spring")
// public interface ContractMapper {
//     ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);


//     @Mapping(source = "id", target = "id")
//     @Mapping(source = "title", target = "title")
//     @Mapping(source = "description", target = "description")
//     @Mapping(source = "assertionText", target = "assertionText")
//     @Mapping(source = "startDate", target = "startDate")
//     @Mapping(source = "endDate", target = "endDate")
//     ContractDTO contractToContractDTO(Contract contract);

//     @Mapping(source = "id", target = "id")
//     @Mapping(source = "title", target = "title")
//     @Mapping(source = "description", target = "description")
//     @Mapping(source = "assertionText", target = "assertionText")
//     @Mapping(source = "startDate", target = "startDate")
//     @Mapping(source = "endDate", target = "endDate")
//     Contract contractDTOToContract(ContractDTO contractDTO);
// }

@Mapper(componentModel = "spring")
public class ContractMapper {

    // Method to map from Contract to ContractDTO
    public ContractDTO contractToContractDTO(Contract contract) {
        if (contract == null) {
            return null;
        }

        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setId(contract.getId());
        contractDTO.setTitle(contract.getTitle());
        contractDTO.setDescription(contract.getDescription());
        contractDTO.setAssertionText(contract.getAssertionText());
        contractDTO.setStartDate(contract.getStartDate());
        contractDTO.setEndDate(contract.getEndDate());
        contractDTO.setCreatorId(contract.getCreatorId());

        return contractDTO;
    }

    // Method to map from ContractDTO to Contract
    public Contract contractDTOToContract(ContractDTO contractDTO) {
        if (contractDTO == null) {
            return null;
        }

        Contract contract = new Contract();
        contract.setId(contractDTO.getId());
        contract.setTitle(contractDTO.getTitle());
        contract.setDescription(contractDTO.getDescription());
        contract.setAssertionText(contractDTO.getAssertionText());
        contract.setStartDate(contractDTO.getStartDate());
        contract.setEndDate(contractDTO.getEndDate());
        contract.setCreatorId(contractDTO.getCreatorId());

        return contract;
    }
}
