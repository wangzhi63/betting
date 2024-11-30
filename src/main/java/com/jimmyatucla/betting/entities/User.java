package com.jimmyatucla.betting.entities;

// User.java
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "creator")
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "user")
    private Set<Bid> bids;

    @OneToMany(mappedBy = "resolvedBy")
    private Set<Resolution> resolutions;

    @OneToOne(mappedBy = "user")
    private Wallet wallet;

    // Getters and Setters
}


