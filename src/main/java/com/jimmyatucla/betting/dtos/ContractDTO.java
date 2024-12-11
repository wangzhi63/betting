package com.jimmyatucla.betting.dtos;


import java.time.LocalDate;

public class ContractDTO {
    private Long id;
    private String title;
    private String description;
    private String assertionText;
    private LocalDate startDate;
    private LocalDate endDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssertionText() {
        return assertionText;
    }

    public void setAssertionText(String assertionText) {
        this.assertionText = assertionText;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assertionText='" + assertionText + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}