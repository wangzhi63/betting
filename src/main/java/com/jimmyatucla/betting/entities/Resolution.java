package com.jimmyatucla.betting.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resolution")
public class Resolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false, insertable = false, updatable = false)
    private Contract contract;

    @Column(nullable = false)
    private String decision;

    @Column(name="status")
    private String status;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "resolved_by", nullable = false)
    private Long resolvedById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by", nullable = false, insertable = false, updatable = false)
    private User resolvedBy;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    } 
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
    public Long getContractId() {
        return contractId;
    }
    public void setContract(Contract contract) {
        this.contract = contract;
    }   
    public Contract getContract() {
        return contract;
    }  
    public void setStatus(String status) {
        this.status = status;
    }  
    public String getStatus() {
        return status;
    }

    public void setResolvedById(Long resolvedById) {
        this.resolvedById = resolvedById;
    }
    public Long getResolvedById() {
        return resolvedById;
    }
    public void setDecision(String decision) {
        this.decision = decision;
    }
    public String getDecision() {
        return decision;
    }   

}