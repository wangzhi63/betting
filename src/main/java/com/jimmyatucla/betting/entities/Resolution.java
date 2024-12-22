package com.jimmyatucla.betting.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resolution")
public class Resolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(nullable = false)
    private String decision;

    @Column(name="status")
    private String status;

    @Column(name = "resolved_at", nullable = false, updatable = false)
    private LocalDateTime resolvedAt;

    @ManyToOne
    @JoinColumn(name = "resolved_by", nullable = false)
    private User resolvedBy;

    // Getters and Setters
}