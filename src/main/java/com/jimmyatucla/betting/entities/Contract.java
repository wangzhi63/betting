package com.jimmyatucla.betting.entities;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name = "assertion_text")
    private String assertionText;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "contract")
    private Set<Bid> bids;

    @OneToOne(mappedBy = "contract")
    private Resolution resolution;


    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assertionText='" + assertionText + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

        // Getters and setters...
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
    
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    
        public String getAssertionText() { return assertionText; }
        public void setAssertionText(String assertionText) { this.assertionText = assertionText; }
    
        public LocalDate getStartDate() { return startDate; }
        public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
        public LocalDate getEndDate() { return endDate; }
        public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}