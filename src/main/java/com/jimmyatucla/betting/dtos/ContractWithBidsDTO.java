package com.jimmyatucla.betting.dtos;


import java.time.LocalDate;

public class ContractWithBidsDTO {
    private Long id;
    private String assertionText;
    private LocalDate endDate; // Added field
    private Double longAmount;
    private Double shortAmount;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getLongAmount() {
        return longAmount;
    }

    public void setLongAmount(Double longAmount) {
        this.longAmount = longAmount;
    }

    public Double getShortAmount() {
        return shortAmount;
    }

    public void setShortAmount(Double shortAmount) {
        this.shortAmount = shortAmount;
    }
}