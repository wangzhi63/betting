package com.jimmyatucla.betting.dtos;

import java.time.LocalDate;

public class ResolutionDTO {

    private Long id;
    private Long contractId;
    private String assertionText;
    private String status;
    private String decision;
    private LocalDate endDate;
    private String CreatorName;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getAssertionText() {
        return assertionText;
    }

    public void setAssertionText(String assertionText) {
        this.assertionText = assertionText;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
  
    public String getCreatorName() {
        return CreatorName;
    }
    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }
}
